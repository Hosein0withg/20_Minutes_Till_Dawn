package com.tilldown.Controller.MenuControl;

import com.tilldown.Controller.GameController;
import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.View.*;
import com.tilldown.View.MenuViews.GameMenu;
import com.tilldown.View.MenuViews.PreGameMenu;

public class PreGameMenuController {
    private PreGameMenu view;

    public void setView(PreGameMenu view) {
        this.view = view;
    }

    public void handlePreGameMenuButtons() {
        if (view != null) {
            if (view.getStartGameButton().isPressed()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getBackButton().isPressed()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameMenu(new GameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    public void handleHeroSelection() {
        if (view != null) {
            switch (view.getSelectedHero()) {
                case "Shana":
                    Game.getCurrentUser().setCurrentHero(Game.characters.get(0));
                    break;
                case "Diamond":
                    Game.getCurrentUser().setCurrentHero(Game.characters.get(1));
                    break;
                case "Scarlet":
                    Game.getCurrentUser().setCurrentHero(Game.characters.get(2));
                    break;
                case "Lilith":
                    Game.getCurrentUser().setCurrentHero(Game.characters.get(3));
                    break;
                case "Dasher":
                    Game.getCurrentUser().setCurrentHero(Game.characters.get(4));
                    break;
            }
        }
    }

    public void handleGunSelection() {
        if (view != null) {
            switch (view.getSelectedGun()) {
                case "Revolver":
                    Game.getCurrentUser().getCurrentHero().setCurrentGun(Game.guns.get(0));
                    break;
                case "Shotgun":
                    Game.getCurrentUser().getCurrentHero().setCurrentGun(Game.guns.get(1));
                    break;
                case "SMG":
                    Game.getCurrentUser().getCurrentHero().setCurrentGun(Game.guns.get(2));
                    break;
            }
        }
    }

    public void handleTimeSelection() {
        if (view != null) {
            switch (view.getSelectedDuration()) {
                case "2":
                    Game.gameDuration = 2;
                    break;
                case "5":
                    Game.gameDuration = 5;
                    break;
                case "10":
                    Game.gameDuration = 10;
                    break;
                case "20":
                    Game.gameDuration = 20;
                    break;
            }
        }
    }
}
