package com.tilldown.Controller.MenuControl;

import com.tilldown.Main;
import com.tilldown.Model.App;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.View.MenuViews.*;

public class GameMenuController {
    private GameMenu view;

    public void setView(GameMenu view) {
        this.view = view;
    }

    public void handleGameMenuButtons() {
        if (view != null) {
            if (view.getSettingsButton().isPressed()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new SettingMenu(GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getBackButton().isPressed()) {
                Main.getMain().getScreen().dispose();
                App.setCurrentUser(null);
                Main.getMain().setScreen(new StartMenu(new StartMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getProfileButton().isPressed()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenu(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getPreGameButton().isPressed()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenu(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getHintButton().isPressed()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new HintMenu(new HintMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getScoreboardButton().isPressed()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ScoreboardMenu(new ScoreboardMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
