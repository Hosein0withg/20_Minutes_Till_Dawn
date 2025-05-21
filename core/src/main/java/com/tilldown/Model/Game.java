package com.tilldown.Model;

import java.util.ArrayList;

public class Game {
    public static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;

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

    public static void setCurrentUser(User currentUser) {
        Game.currentUser = currentUser;
    }
}
