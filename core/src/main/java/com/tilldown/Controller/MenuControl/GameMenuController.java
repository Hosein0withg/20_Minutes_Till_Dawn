package com.tilldown.Controller.MenuControl;

import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.View.*;

public class GameMenuController {
    private GameMenu view;

    public void setView(GameMenu view) {
        this.view = view;
    }

    public void handleGameMenuButtons() {
        if (view != null) {
            if (view.getSettingsButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MusicMenu(GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getBackButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Game.setCurrentUser(null);
                Main.getMain().setScreen(new StartMenu(new StartMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getProfileButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenu(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getPreGameButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenu(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
