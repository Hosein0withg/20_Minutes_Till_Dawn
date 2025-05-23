package com.tilldown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class Bullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private Sprite sprite = new Sprite(texture);
    private int damage = 5;
    private float directionX;
    private float directionY;
    private float speed = 10;

    public Bullet(float startX, float startY, float targetX, float targetY){
        sprite.setSize(20, 20);
        sprite.setPosition(startX, startY);

        float worldTargetY = Gdx.graphics.getHeight() - targetY;

        float angle = MathUtils.atan2(worldTargetY - startY, targetX - startX);
        this.directionX = MathUtils.cos(angle) * speed;
        this.directionY = MathUtils.sin(angle) * speed;
    }

    public void updatePosition() {
        sprite.setX(sprite.getX() + directionX);
        sprite.setY(sprite.getY() + directionY);
    }

    public float getSpriteX() { return sprite.getX(); }

    public float getSpriteY() { return sprite.getY(); }

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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getDirectionX() {
        return directionX;
    }

    public void setDirectionX(float directionX) {
        this.directionX = directionX;
    }

    public float getDirectionY() {
        return directionY;
    }

    public void setDirectionY(float directionY) {
        this.directionY = directionY;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
