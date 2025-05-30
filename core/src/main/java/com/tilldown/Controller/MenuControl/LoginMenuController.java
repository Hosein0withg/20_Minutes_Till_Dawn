package com.tilldown.Controller.MenuControl;

import com.tilldown.Main;
import com.tilldown.Model.*;
import com.tilldown.View.MenuViews.ForgetPassword;
import com.tilldown.View.MenuViews.GameMenu;
import com.tilldown.View.MenuViews.LoginMenu;

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
                        Main.getMain().setScreen(new GameMenu(new GameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
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
        if (App.findUser(username) == null) {
            return new Result(false, "Username is wrong");
        }
        User player = App.findUser(username);
        if (!player.getPassword().equals(password)) {
            return new Result(false, "password is wrong");
        }
        App.setCurrentUser(player);
        return new Result(true, "login successful!");
    }
}
