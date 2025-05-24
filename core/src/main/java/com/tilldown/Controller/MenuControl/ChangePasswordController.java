package com.tilldown.Controller.MenuControl;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tilldown.Main;
import com.tilldown.Model.*;
import com.tilldown.View.MenuViews.ChangePassword;
import com.tilldown.View.MenuViews.ProfileMenu;

public class ChangePasswordController {
    private ChangePassword view;

    public void setView(ChangePassword view) {
        this.view = view;
    }

    public void handleChangePasswordMenuButton() {
        if (view != null) {
            view.getChangePassword().addListener(new ChangeListener() {
                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    String password = view.password.getText();
                    if (Regex.password.getMatcher(password) == null) {
                        view.errorLabel.setText("Password is too easy!");
                    } else {
                        User currentUser = Game.findUser(Game.getCurrentUser().getUsername());
                        if (currentUser != null) {
                            currentUser.setPassword(password);
                            Main.getMain().setScreen(new ProfileMenu(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                        }
                    }
                }
            });
        }
    }
}
