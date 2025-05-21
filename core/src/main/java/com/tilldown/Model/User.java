package com.tilldown.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

import static com.tilldown.Model.GameAssetManager.*;

public class User {
    private String username;
    private String password;
    private final String securityQuestion;
    private final String securityAnswer;
    private Texture avatar;
    private int points;
    public ArrayList<Player> characters = new ArrayList<>();
    private Player currentHero;

    public User(String username, String password, String securityQuestion, String securityAnswer, String avatarPath) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.avatar = new Texture(avatarPath);
        points = 0;
        this.characters.add(new Player(4, 4, new Animation<>(0.1f, new Texture(character1[0]), new Texture(character1[1]), new Texture(character1[2]), new Texture(character1[3]), new Texture(character1[4]), new Texture(character1[5]))));
        this.characters.add(new Player(7, 1, new Animation<>(0.1f, new Texture(character2[0]), new Texture(character2[1]), new Texture(character2[2]), new Texture(character2[3]), new Texture(character2[4]), new Texture(character2[5]))));
        this.characters.add(new Player(3, 5, new Animation<>(0.1f, new Texture(character3[0]), new Texture(character3[1]), new Texture(character3[2]), new Texture(character3[3]), new Texture(character3[4]), new Texture(character3[5]))));
        this.characters.add(new Player(5, 3, new Animation<>(0.1f, new Texture(character4[0]), new Texture(character4[1]), new Texture(character4[2]), new Texture(character4[3]), new Texture(character4[4]), new Texture(character4[5]))));
        this.characters.add(new Player(2, 10, new Animation<>(0.1f, new Texture(character5[0]), new Texture(character5[1]), new Texture(character5[2]), new Texture(character5[3]), new Texture(character5[4]), new Texture(character5[5]))));
        this.currentHero = characters.get(0);
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
}
