package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
import com.tilldown.Controller.MenuControl.EndGameMenuController;
import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.View.GameView;
import com.tilldown.View.MenuViews.EndGameMenu;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private float gameTime = Game.gameDuration * 60;


    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(Game.getCurrentUser().getCurrentHero(), this);
        worldController = new WorldController(playerController);
        weaponController = new WeaponController(Game.getCurrentUser().getCurrentHero().getCurrentGun(), this);
        Game.gameTimePassed = 0;
    }

    public void updateGame(float delta) {
        if (view != null) {
            worldController.update(delta);
            playerController.update();
            weaponController.update();
            gameTime -= Gdx.graphics.getDeltaTime();
            Game.gameTimePassed = (int) ((Game.gameDuration * 60) - gameTime);

            if (gameTime <= 0) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new EndGameMenu(new EndGameMenuController(), GameAssetManager.getGameAssetManager().getSkin(), true));
            }
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

    public float getGameTime() {
        return gameTime;
    }

    public GameView getView() {
        return view;
    }

    public void cheatGameTime() {
        this.gameTime -= 60;
    }

    public WorldController getWorldController() {
        return worldController;
    }
}
