package com.tilldown.Controller.MenuControl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.View.MenuViews.ChangeAvatar;
import com.tilldown.View.MenuViews.ProfileMenu;
import java.awt.*;

public class ChangeAvatarController {
    private ChangeAvatar view;

    public void setView(ChangeAvatar view) {
        this.view = view;
    }

    public void selectCustomAvatar() {
        FileDialog fileDialog = new FileDialog((Frame) null, "Select Avatar", FileDialog.LOAD);
        fileDialog.setVisible(true);

        String filePath = fileDialog.getDirectory() + fileDialog.getFile();
        if (filePath != null) {
            Game.getCurrentUser().setAvatar(new Texture(filePath));
        }
    }

    public void handleBackButton() {
        view.getBackButton().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().setScreen(new ProfileMenu(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
    }
}
