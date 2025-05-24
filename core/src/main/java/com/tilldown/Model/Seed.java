package com.tilldown.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Seed {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getSeed());
    private Sprite sprite = new Sprite(texture);
    private float posX;
    private float posY;
    private CollisionRect collisionRect;

    public Seed(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        collisionRect = new CollisionRect(posX, posY, sprite.getWidth(), sprite.getHeight());
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

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }
}
