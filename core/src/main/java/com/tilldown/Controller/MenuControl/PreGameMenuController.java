package com.tilldown.Controller.MenuControl;

import com.badlogic.gdx.Gdx;
import com.tilldown.Controller.GameController;
import com.tilldown.Main;
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
                Main.getMain().setScreen(new GameView(new GameController()));
            }
        }
    }
}
