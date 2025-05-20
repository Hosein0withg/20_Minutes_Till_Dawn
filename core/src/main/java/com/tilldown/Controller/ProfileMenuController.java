package com.tilldown.Controller;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.Model.Player;
import com.tilldown.View.ChangePassword;
import com.tilldown.View.ChangeUsername;
import com.tilldown.View.GameMenu;
import com.tilldown.View.ProfileMenu;

public class ProfileMenuController {
    private ProfileMenu view;

    public void setView(ProfileMenu view) {
        this.view = view;
    }

    public void handleProfileMenuButtons() {
        if (view != null) {
            if (view.getBackButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameMenu(new GameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getChangeUsername().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ChangeUsername(new ChangeUsernameController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getChangePassword().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ChangePassword(new ChangePasswordController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    public void updateInformation() {
        Player player = Game.getCurrentPlayer();
        String output = "";
        if (player != null) {
            output += "username: " + player.getUsername() + "\n";
            output += "point: " + player.getPoints();
        }
        view.setInformation(new Label(output, GameAssetManager.getGameAssetManager().getSkin()));
    }
}
