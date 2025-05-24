package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.tilldown.Controller.MenuControl.PauseMenuController;
import com.tilldown.Main;
import com.tilldown.Model.*;
import com.tilldown.View.MenuViews.PauseMenu;

public class PlayerController {
    private Player player;
    private GameController gameController;

    public PlayerController(Player player, GameController gameController) {
        this.player = player;
        this.gameController = gameController;
    }

    public void update() {
        player.updateInvincibility(Gdx.graphics.getDeltaTime());
        player.getPlayerSprite().setPosition(player.getPosX(), player.getPosY());
        player.getPlayerSprite().draw(Main.getBatch());
        updateLevel();

        if (player.isPlayerIdle()) {
            idleAnimation();
        }
        handlePlayerInput();
    }

    public void updateLevel() {
        if (player.getXP() >= player.calculateNextLevelXP()) {
            player.setXP(player.getXP() - player.calculateNextLevelXP());
            player.setLevel(player.getLevel() + 1);
            gameController.getView().showAbility = true;
            gameController.getView().ability = player.setRandomAbility();
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    gameController.getView().showAbility = false;
                }
            }, 10);
        }
    }


    public void handlePlayerInput() {
        float newX = player.getPosX();
        float newY = player.getPosY();
        float speed = player.getSpeed();
        if (Game.getCurrentUser().isNumberController()) {
            if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8)) {
                newY += speed;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)) {
                newY -= speed;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)) {
                newX -= speed;
                player.getPlayerSprite().flip(true, false);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)) {
                newX += speed;
            }
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                newY += speed;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                newY -= speed;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                newX -= speed;
                player.getPlayerSprite().flip(true, false);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                newX += speed;
            }
        }

        CollisionRect nextRect = new CollisionRect(newX, newY, player.getRect().width, player.getRect().height);

        for (Tree tree : Game.trees) {
            if (nextRect.overlap(tree.getRect())) {
                if (!player.isInvincible()) {
                    player.setHP(player.getHP() - 1);
                    player.startInvincibility();
                }
                return;
            }
        }

        for (TentacleMonster monster : gameController.getWorldController().monsters) {
            if (nextRect.overlap(monster.getRect())) {
                if (!player.isInvincible()) {
                    player.setHP(player.getHP() - 1);
                    player.startInvincibility();
                }
            }
        }

        for (int i = 0; i < Game.getCurrentUser().seedsOnMap.size(); i++) {
            Seed seed = Game.getCurrentUser().seedsOnMap.get(i);
            if (nextRect.overlap(seed.getCollisionRect())) {
                player.setXP(player.getXP() + 3);
                Game.getCurrentUser().seedsOnMap.remove(seed);
                i++;
            }
        }

        float minX = 0;
        float minY = 0;
        float maxX = Gdx.graphics.getWidth() - player.getRect().width;
        float maxY = Gdx.graphics.getHeight() - player.getRect().height;
        newX = MathUtils.clamp(newX, minX, maxX);
        newY = MathUtils.clamp(newY, minY, maxY);
        player.setPosX(newX);
        player.setPosY(newY);

        gameController.getWeaponController().updateWeaponPosition(
            player.getPosX(), player.getPosY());

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            gameController.getWeaponController().reloadGun();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new PauseMenu(new PauseMenuController(), GameAssetManager.getGameAssetManager().getSkin(), gameController.getView()));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            Game.getCurrentUser().setGameDuration(Game.getCurrentUser().gameDuration);
            gameController.cheatGameTime();
            Game.getCurrentUser().gameTimePassed = (int) ((Game.getCurrentUser().gameDuration * 60) - gameController.getGameTime());
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            player.setLevel(player.getLevel() + 1);
            player.setXP(0);
            gameController.getView().showAbility = true;
            gameController.getView().ability = player.setRandomAbility();
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    gameController.getView().showAbility = false;
                }
            }, 10);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
            if (player.getHP() <= 0) {
                player.setHP(3);
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            player.boostPlayerSpeed();
            gameController.getView().showAbility = true;
            gameController.getView().ability = "Speedy";
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    gameController.getView().showAbility = false;
                }
            }, 10);
        }

    }


    public void idleAnimation() {
        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getCharacter_idle_animation();

        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        } else {
            player.setTime(0);
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
