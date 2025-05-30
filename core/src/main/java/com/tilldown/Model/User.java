package com.tilldown.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private final String securityQuestion;
    private final String securityAnswer;
    private Texture avatar;
    private int score;
    private int kill;
    private int livedTime;
    private boolean autoReload = false;
    private boolean numberController = false;
    private boolean sfx = true;
    public ArrayList<String> gainedAbilities = new ArrayList<>();

    public User(String username, String password, String securityQuestion, String securityAnswer, String avatarPath, int score, int kill, int livedTime) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.avatar = new Texture(avatarPath);
        App.setCurrentHero(Game.characters.get(0));
        this.score = score;
        this.kill = kill;
        this.livedTime = livedTime;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player getCurrentHero() {
        return App.getCurrentHero();
    }

    public void setCurrentHero(Player currentHero) {
        App.setCurrentHero(currentHero);
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public boolean isNumberController() {
        return numberController;
    }

    public void setNumberController(boolean numberController) {
        this.numberController = numberController;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public int getLivedTime() {
        return livedTime;
    }

    public void setLivedTime(int livedTime) {
        this.livedTime = livedTime;
    }

    public boolean isSfx() {
        return sfx;
    }

    public void setSfx(boolean sfx) {
        this.sfx = sfx;
    }
}
