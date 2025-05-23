package com.tilldown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    public static final String[] avatarOptions = {
        "avatar/1.png",
        "avatar/2.png",
        "avatar/3.png",
        "avatar/4.png"
    };
    public static final String[] musicOptions = {
        "music/1.mp3",
        "music/2.mp3",
        "music/3.mp3",
    };

    public static String[] character1 = {"1/Idle_0.png", "1/Idle_1.png", "1/Idle_2.png", "1/Idle_3.png", "1/Idle_4.png", "1/Idle_5.png"};
    public static String[] character2 = {"2/Idle_0.png", "2/Idle_1.png", "2/Idle_2.png", "2/Idle_3.png", "2/Idle_4.png", "2/Idle_5.png"};
    public static String[] character3 = {"3/Idle_0.png", "3/Idle_1.png", "3/Idle_2.png", "3/Idle_3.png", "3/Idle_4.png", "3/Idle_5.png"};
    public static String[] character4 = {"4/Idle_0.png", "4/Idle_1.png", "4/Idle_2.png", "4/Idle_3.png", "4/Idle_4.png", "4/Idle_5.png"};
    public static String[] character5 = {"5/Idle_0.png", "5/Idle_1.png", "5/Idle_2.png", "5/Idle_3.png", "5/Idle_4.png", "5/Idle_5.png"};

    public static String[] tentacleMonster = {"tentacle/TentacleIdle0.png", "tentacle/TentacleIdle1.png", "tentacle/TentacleIdle2.png", "tentacle/TentacleIdle3.png"};

    private final String tree = "tree.png";
    private final String smg = "gun/SMG.png";
    private final String revolver = "gun/Revolver.png";
    private final String shotgun = "gun/Shotgun.png";

    private final String bullet = "bullet.png";


    private GameAssetManager() {

    }

    public static GameAssetManager getGameAssetManager() {
        if (gameAssetManager == null) {
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public Animation<Texture> getCharacter_idle_animation() {
        return Game.getCurrentUser().getCurrentHero().getCharacter_idle_frames();
    }

    public Animation<Texture> getTentacle_idle_animation() {
        return new Animation<>(0.1f, new Texture(tentacleMonster[0]), new Texture(tentacleMonster[1]), new Texture(tentacleMonster[2]), new Texture(tentacleMonster[3]));
    }

    public String getSmg() {
        return smg;
    }

    public String getRevolver() {
        return revolver;
    }

    public String getShotgun() {
        return shotgun;
    }

    public String getBullet() {
        return bullet;
    }

    public String getTree() {
        return tree;
    }
}

