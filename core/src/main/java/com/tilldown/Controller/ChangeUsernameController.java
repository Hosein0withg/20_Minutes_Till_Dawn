package com.tilldown.Controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.Model.Player;
import com.tilldown.Model.Result;
import com.tilldown.View.*;

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
                    if (Game.getPlayer(username) != null) {
                        view.errorLabel.setText("Username already exists");
                    } else {
                        Player currentPlayer = Game.getPlayer(Game.getCurrentPlayer().getUsername());
                        if (currentPlayer != null) {
                            currentPlayer.setUsername(username);
                            Main.getMain().setScreen(new ProfileMenu(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                        }
                    }
                }
            });
        }
    }

}
