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

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }
}
