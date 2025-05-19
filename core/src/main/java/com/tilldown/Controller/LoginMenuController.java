package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
import com.tilldown.Main;
import com.tilldown.Model.*;
import com.tilldown.View.ForgetPassword;
import com.tilldown.View.LoginMenu;
import com.tilldown.View.SignupMenu;

import java.util.Random;

import static com.tilldown.Model.GameAssetManager.AVATAR_OPTIONS;

public class LoginMenuController {
    private LoginMenu view;

    public void setView(LoginMenu view) {
        this.view = view;
    }

    public void handleLoginMenuButton(String username, String password) {
        if (view != null) {
            if (view.getForgetButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ForgetPassword(new ForgetPasswordController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else {
                while (view.getLoginButton().isChecked()) {
                    Main.getMain().getScreen().dispose();
                    Result result = login(username, password);
                    if (result.success) {
                        Gdx.app.exit();
                        //Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                        break;
                    } else {
                        view.getLoginButton().setChecked(false);
                        view.errorLabel.setText(result.message);
                    }
                }
            }
        }
    }

    public Result login(String username, String password) {
        if (Game.getPlayer(username) == null) {
            return new Result(false, "Username is wrong");
        }
        Player player = Game.getPlayer(username);
        if (!player.getPassword().equals(password)) {
            return new Result(false, "password is wrong");
        }
        Game.setCurrentPlayer(player);
        return new Result(true, "login successful!");
    }
}
