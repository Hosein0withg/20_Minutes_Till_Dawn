package com.tilldown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TentacleMonster {
    public Texture texture = new Texture(GameAssetManager.tentacleMonster[0]);
    public Sprite sprite = new Sprite(texture);
    private float posX = 0;
    private float posY = 0;
    private CollisionRect rect;
    public static final int spawnTime = 3;
    private static float spawnRate = 0;
    private int HP = 25;
    private float time = 0;
    private int speed = 2;

    public TentacleMonster() {
        sprite.setPosition((float) Gdx.graphics.getWidth() / 30, (float) Gdx.graphics.getHeight() / 30);
        sprite.setSize(texture.getWidth() * 2, texture.getHeight() * 2);
        rect = new CollisionRect((float) Gdx.graphics.getWidth() / 30, (float) Gdx.graphics.getHeight() / 30,
            (float) (texture.getWidth() * 2), (float) (texture.getHeight() * 2));
    }

    public void moveMonster() {

    }


    public static float getSpawnRate() {
        return (float) Game.getCurrentUser().gameTimePassed / 30;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }

    public static void setSpawnRate(float spawnRate) {
        TentacleMonster.spawnRate = spawnRate;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
