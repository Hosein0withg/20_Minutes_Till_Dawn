package com.tilldown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class User {
    private String username;
    private String password;
    private final String securityQuestion;
    private final String securityAnswer;
    private Texture avatar;
    private int points;

    public User(String username, String password, String securityQuestion, String securityAnswer, String avatarPath) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.avatar = new Texture(avatarPath);
        points = 0;
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
}
