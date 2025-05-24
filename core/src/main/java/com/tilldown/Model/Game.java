package com.tilldown.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tilldown.View.SpawnTree;

import java.util.ArrayList;

import static com.tilldown.Model.GameAssetManager.*;
import static com.tilldown.Model.GameAssetManager.character5;

public class Game {
    public static ArrayList<Player> characters;
    public static ArrayList<Weapon> guns;
    public static ArrayList<Tree> trees;
    public static ArrayList<Seed> seedsOnMap;
    public static int gameDuration = 2;
    public static int gameTimePassed = 0;

    static {
        characters = new ArrayList<>();
        guns = new ArrayList<>();
        trees = new ArrayList<>();
        seedsOnMap = new ArrayList<>();
        initialize();
    }

    public static void clear() {
        characters.clear();
        guns.clear();
        trees.clear();
        seedsOnMap.clear();
        initialize();
    }

    private static void initialize() {
        guns.add(new Weapon(20, 1, 1, 6));
        guns.add(new Weapon(10, 4, 1, 2));
        guns.add(new Weapon(8, 1, 2, 24));

        characters.add(new Player(4, 4, new Animation<>(0.1f, new Texture(character1[0]), new Texture(character1[1]), new Texture(character1[2]), new Texture(character1[3]), new Texture(character1[4]), new Texture(character1[5])), character1[0]));
        characters.add(new Player(7, 1, new Animation<>(0.1f, new Texture(character2[0]), new Texture(character2[1]), new Texture(character2[2]), new Texture(character2[3]), new Texture(character2[4]), new Texture(character2[5])), character2[0]));
        characters.add(new Player(3, 5, new Animation<>(0.1f, new Texture(character3[0]), new Texture(character3[1]), new Texture(character3[2]), new Texture(character3[3]), new Texture(character3[4]), new Texture(character3[5])), character3[0]));
        characters.add(new Player(5, 3, new Animation<>(0.1f, new Texture(character4[0]), new Texture(character4[1]), new Texture(character4[2]), new Texture(character4[3]), new Texture(character4[4]), new Texture(character4[5])), character4[0]));
        characters.add(new Player(2, 10, new Animation<>(0.1f, new Texture(character5[0]), new Texture(character5[1]), new Texture(character5[2]), new Texture(character5[3]), new Texture(character5[4]), new Texture(character5[5])), character5[0]));

        SpawnTree treeSpawner = new SpawnTree();
        trees = treeSpawner.getTrees();
    }

    public static User getCurrentUser() {
        return App.getCurrentUser();
    }
}
