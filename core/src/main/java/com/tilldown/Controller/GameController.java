package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
import com.tilldown.Model.Game;
import com.tilldown.Model.Player;
import com.tilldown.Model.Weapon;
import com.tilldown.View.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private float gameTime = Game.getCurrentUser().gameDuration * 60;


    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(Game.getCurrentUser().getCurrentHero());
        worldController = new WorldController(playerController);
        weaponController = new WeaponController(new Weapon());
    }

    public void updateGame() {
        if (view != null) {
            worldController.update();
            playerController.update();
            weaponController.update();
            gameTime -= Gdx.graphics.getDeltaTime();

            if (gameTime <= 0) {
                Gdx.app.exit();
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
}
