package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.Model.Player;
import com.tilldown.View.GameMenu;
import com.tilldown.View.LoginMenu;
import com.tilldown.View.SignupMenu;
import com.tilldown.View.StartMenu;

public class StartMenuController {
    private StartMenu view;

    public void setView(StartMenu view) {
        this.view = view;
    }

    public void handleStartMenuButtons() {
        if (view != null) {
            if (view.getExitButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Gdx.app.exit();
            } else if (view.getSignupButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new SignupMenu(new SignupMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getLoginButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginMenu(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getGuestButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                setGuest();
                Main.getMain().setScreen(new GameMenu(new GameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    private void setGuest() {
        Player guest = new Player("Guest", "Guest", "Guest", "Guest", "D:\\20min\\assets\\avatar\\2.png");
        Game.players.add(guest);
        Game.setCurrentPlayer(guest);
    }
}
