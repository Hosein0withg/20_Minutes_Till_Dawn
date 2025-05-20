package com.tilldown.Controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.Model.Player;
import com.tilldown.Model.Regex;
import com.tilldown.View.*;

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
                        Player currentPlayer = Game.getPlayer(Game.getCurrentPlayer().getUsername());
                        if (currentPlayer != null) {
                            currentPlayer.setPassword(password);
                            Main.getMain().setScreen(new ProfileMenu(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                        }
                    }
                }
            });
        }
    }
}
