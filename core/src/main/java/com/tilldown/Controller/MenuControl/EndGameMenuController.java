package com.tilldown.Controller.MenuControl;

import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.View.MenuViews.EndGameMenu;
import com.tilldown.View.MenuViews.GameMenu;

public class EndGameMenuController {
    private EndGameMenu view;

    public void setView(EndGameMenu view) {
        this.view = view;
    }

    public void handleEndGameMenuButtons() {
        if (view != null) {
            if (view.getGoToGameMenu().isPressed()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameMenu(new GameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                Game.clear();
            }
        }
    }
}
