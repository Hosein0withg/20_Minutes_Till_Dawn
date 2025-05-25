package com.tilldown.Controller.MenuControl;

import com.tilldown.Main;
import com.tilldown.Model.App;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.Model.User;
import com.tilldown.View.MenuViews.*;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreboardMenuController {
    private ScoreboardMenu view;

    public void setView(ScoreboardMenu view) {
        this.view = view;
    }

    public void handleScoreboardMenuButtons() {
        if (view != null) {
            if (view.getBackButton().isPressed()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameMenu(new GameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getSortByUsername().isPressed()) {
                view.topUsers.sort(Comparator.comparing(User::getUsername));
                view.showScoreboard();
            } else if (view.getSortByScore().isPressed()) {
                view.topUsers.sort(Comparator.comparing(User::getScore).reversed());
                view.showScoreboard();
            } else if (view.getSortByKill().isPressed()) {
                view.topUsers.sort(Comparator.comparing(User::getKill).reversed());
                view.showScoreboard();
            } else if (view.getSortByLivedTime().isPressed()) {
                view.topUsers.sort(Comparator.comparing(User::getLivedTime).reversed());
                view.showScoreboard();
            }
        }
    }

    public ArrayList<User> getTopUsers() {
        App.users.sort(Comparator.comparing(User::getScore).reversed());
        ArrayList<User> topUsers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            topUsers.add(App.users.get(i));
        }
        return topUsers;
    }
}
