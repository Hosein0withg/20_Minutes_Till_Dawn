package com.tilldown.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private final String securityQuestion;
    private final String securityAnswer;
    private Texture avatar;
    private int points;
    private Player currentHero;
    public int gameDuration = 2;
    public int gameTimePassed = 0;
    private boolean autoReload = false;
    public ArrayList<String> gainedAbilities = new ArrayList<>();

    public User(String username, String password, String securityQuestion, String securityAnswer, String avatarPath) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.avatar = new Texture(avatarPath);
        points = 0;
        this.currentHero = Game.characters.get(0);

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public TextureRegionDrawable getAvatarDrawable() {
        return new TextureRegionDrawable(new TextureRegion(this.avatar));
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(Texture avatar) {
        this.avatar = avatar;
    }

    public Texture getAvatar() {
        return avatar;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Player getCurrentHero() {
        return currentHero;
    }

    public void setCurrentHero(Player currentHero) {
        this.currentHero = currentHero;
    }

    public void setGameDuration(int gameDuration) {
        this.gameDuration = gameDuration;
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }
}
