package com.tilldown.Model;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class App {
    public static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;
    private static Player currentHero;

    static {
        String[] name = {
            "Alice", "Bob", "Charlie", "David", "Emma",
            "Fiona", "George", "Hannah", "Isaac", "Julia",
            "Kevin", "Laura", "Michael", "Nathan", "Olivia"
        };
        for (int i = 0; i < 15; i++) {
            users.add(new User(name[i], name[i], name[i], name[i], "avatar/3.png", MathUtils.random(1, 500), MathUtils.random(1, 100), MathUtils.random(1, 1200)));
        }
    }

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
