package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
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
                Gdx.app.exit();
                //Main.getMain().setScreen(new MusicMenu(GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
