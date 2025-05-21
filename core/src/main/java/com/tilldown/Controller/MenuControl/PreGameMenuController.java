package com.tilldown.Controller.MenuControl;

import com.badlogic.gdx.Gdx;
import com.tilldown.Controller.GameController;
import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.View.*;

public class PreGameMenuController {
    private PreGameMenu view;

    public void setView(PreGameMenu view) {
        this.view = view;
    }

    public void handlePreGameMenuButtons() {
        if (view != null) {
            if (view.getStartGameButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    public void handleHeroSelection() {
        if (view != null) {
            switch (view.getSelectedHero()) {
                case "Shana":
                    Game.getCurrentUser().setCurrentHero(Game.getCurrentUser().characters.get(0));
                    break;
                case "Diamond":
                    Game.getCurrentUser().setCurrentHero(Game.getCurrentUser().characters.get(1));
                    break;
                case "Scarlet":
                    Game.getCurrentUser().setCurrentHero(Game.getCurrentUser().characters.get(2));
                    break;
                case "Lilith":
                    Game.getCurrentUser().setCurrentHero(Game.getCurrentUser().characters.get(3));
                    break;
                case "Dasher":
                    Game.getCurrentUser().setCurrentHero(Game.getCurrentUser().characters.get(4));
                    break;
            }
        }
    }
}
