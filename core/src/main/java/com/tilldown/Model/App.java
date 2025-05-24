package com.tilldown.Model;

import java.util.ArrayList;

public class App {
    public static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;
    private static Player currentHero;

    public static User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static Player getCurrentHero() {
        return currentHero;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }

    public static void setCurrentHero(Player currentHero) {
        App.currentHero = currentHero;
    }
}
