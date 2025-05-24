package com.tilldown.Controller.MenuControl;

import com.tilldown.Main;
import com.tilldown.Model.App;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.Model.User;
import com.tilldown.View.MenuViews.ForgetPassword;
import com.tilldown.View.MenuViews.LoginMenu;

public class ForgetPasswordController {
    private ForgetPassword view;

    public void setView(ForgetPassword view) {
        this.view = view;
    }

    public void handleSubmitUsernameButton(String username) {
        if (view != null && view.getSubmitUsernameButton().isChecked()) {
            User player = App.findUser(username);

            if (player != null) {
                view.questionLabel.setText("Security Question: " + player.getSecurityQuestion()); // Show the question
                view.answer.setVisible(true);
                view.getSubmitAnswerButton().setVisible(true);
            } else {
                view.errorLabel.setText("Username not found!");
            }

            view.getSubmitUsernameButton().setChecked(false);
        }
    }


    public void handleSubmitAnswerButton(String username, String answer) {
        if (view != null && view.getSubmitAnswerButton().isChecked()) {
            User player = App.findUser(username);

            if (player != null && player.getSecurityAnswer().equals(answer)) {
                view.password.setVisible(true);
                view.getSubmitPasswordButton().setVisible(true);
                view.errorLabel.setText("");
            } else {
                view.errorLabel.setText("Incorrect security answer!");
            }

            view.getSubmitAnswerButton().setChecked(false);
        }
    }


    public void handleSubmitPasswordButton(String username, String password) {
        if (view != null && view.getSubmitPasswordButton().isChecked()) {
            User player = App.findUser(username);

            if (player != null) {
                player.setPassword(password);
                view.errorLabel.setText("Password successfully changed!");

                Main.getMain().setScreen(new LoginMenu(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }

            view.getSubmitPasswordButton().setChecked(false);
        }
    }

}
