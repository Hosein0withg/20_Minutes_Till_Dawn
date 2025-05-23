package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Timer;
import com.tilldown.Controller.MenuControl.PreGameMenuController;
import com.tilldown.Main;
import com.tilldown.Model.*;
import com.tilldown.View.PreGameMenu;

public class PlayerController {
    private Player player;
    private GameController gameController;

    public PlayerController(Player player, GameController gameController) {
        this.player = player;
        this.gameController = gameController;
    }

    public void update() {
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
            gameController.getView().ability = player.setAbility();
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
                newY -= speed;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)) {
                newY += speed;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)) {
                newX += speed;
                player.getPlayerSprite().flip(true, false);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)) {
                newX -= speed;
            }
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                newY -= speed;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                newY += speed;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                newX += speed;
                player.getPlayerSprite().flip(true, false);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                newX -= speed;
            }
        }

        CollisionRect nextRect = new CollisionRect(newX, newY, player.getRect().width, player.getRect().height);

        for (Tree tree : Game.trees) {
            if (nextRect.collidesWith(tree.getRect())) {
                player.setHP(player.getHP() - 1);
                return;
            }
        }
        player.setPosX(newX);
        player.setPosY(newY);



        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            gameController.getWeaponController().reloadGun();
        } else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Main.getMain().getScreen().dispose();
            gameController.getView().pause();
            Main.getMain().setScreen(new PreGameMenu(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
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
