package com.tilldown.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tree {
    private Texture texture;
    private Sprite sprite;
    private float worldX;
    private float worldY;
    private CollisionRect rect;

    public Tree(int x, int y) {
        this.texture = new Texture(GameAssetManager.getGameAssetManager().getTree());
        this.sprite = new Sprite(texture);
        this.worldX = x;
        this.worldY = y;
        this.sprite.setPosition(x, y);
        this.sprite.setSize(texture.getWidth(), texture.getHeight());
        this.rect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
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

    public float getWorldX() {
        return worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    public CollisionRect getRect() {
        return rect;
    }
}
