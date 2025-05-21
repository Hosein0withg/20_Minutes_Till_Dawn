package com.tilldown.Controller.MenuControl;

import com.tilldown.Main;
import com.tilldown.Model.*;
import com.tilldown.View.SignupMenu;
import com.tilldown.View.StartMenu;

import java.util.Random;

import static com.tilldown.Model.GameAssetManager.avatarOptions;

public class SignupMenuController {
    private SignupMenu view;

    public void setView(SignupMenu view) {
        this.view = view;
    }

    public void handleSignupMenuButton(String username, String password, String securityQuestion, String securityAnswer) {
        if (view != null) {
            while (view.getSignupButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Result result = signup(username, password, securityQuestion, securityAnswer);
                if (result.success) {
                    Main.getMain().setScreen(new StartMenu(new StartMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                    break;
                } else {
                    view.getSignupButton().setChecked(false);
                    view.errorLabel.setText(result.message);
                }
            }
        }
    }

    public Result signup(String username, String password, String securityQuestion, String securityAnswer) {
        if (Game.findUser(username) != null) {
            return new Result(false, "Username is already in use");
        } else if (Regex.password.getMatcher(password) == null) {
            return new Result(false, "password is too easy!");
        }
        String randomAvatar = avatarOptions[new Random().nextInt(avatarOptions.length)];
        User player = new User(username, password, securityQuestion, securityAnswer, randomAvatar);
        Game.users.add(player);

        return new Result(true, "Signup successful!");
    }


}
