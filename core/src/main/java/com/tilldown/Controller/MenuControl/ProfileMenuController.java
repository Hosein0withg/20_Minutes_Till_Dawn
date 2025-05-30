package com.tilldown.Controller.MenuControl;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.tilldown.Main;
import com.tilldown.Model.App;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.Model.User;
import com.tilldown.View.MenuViews.*;

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
            } else if (view.getChangeAvatar().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ChangeAvatar(new ChangeAvatarController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getDeleteAccount().isChecked()) {
                App.users.remove(Game.getCurrentUser());
                App.setCurrentUser(null);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new StartMenu(new StartMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    public void updateInformation() {
        User player = Game.getCurrentUser();
        String output = "";
        if (player != null) {
            output += "username: " + player.getUsername() + "\n";
            output += "point: " + player.getScore();
        }
        view.setInformation(new Label(output, GameAssetManager.getGameAssetManager().getSkin()));
    }
}
