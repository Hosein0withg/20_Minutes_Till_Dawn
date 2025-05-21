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

    private final String smg = "smg/SMGStill.png";
    private final Texture smgTexture = new Texture(smg);

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

    public String getCharacter_idle0() {
        switch (Game.getCurrentUser().characters.indexOf(Game.getCurrentUser().getCurrentHero())) {
            case 0:
                return character1[0];
            case 1:
                return character2[0];
            case 2:
                return character3[0];
            case 3:
                return character4[0];
            case 4:
                return character5[0];
        }
        return "";
    }

    public Texture getSmgTexture() {
        return smgTexture;
    }

    public String getSmg() {
        return smg;
    }

    public String getBullet() {
        return bullet;
    }
}

