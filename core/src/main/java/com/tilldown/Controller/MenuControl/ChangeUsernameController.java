package com.tilldown.Controller.MenuControl;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tilldown.Main;
import com.tilldown.Model.App;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.Model.User;
import com.tilldown.View.MenuViews.ChangeUsername;
import com.tilldown.View.MenuViews.ProfileMenu;

public class ChangeUsernameController {
    private ChangeUsername view;

    public void setView(ChangeUsername view) {
        this.view = view;
    }

    public void handleChangeUsernameMenuButton() {
        if (view != null) {
            view.getChangeUsername().addListener(new ChangeListener() {
                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    String username = view.username.getText();
                    if (App.findUser(username) != null) {
                        view.errorLabel.setText("Username already exists");
                    } else {
                        User currentUser = App.findUser(Game.getCurrentUser().getUsername());
                        if (currentUser != null) {
                            currentUser.setUsername(username);
                            Main.getMain().setScreen(new ProfileMenu(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                        }
                    }
                }
            });
        }
    }

}
