package com.tilldown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private Sprite sprite = new Sprite(texture);
    private int damage;
    private float directionX;
    private float directionY;
    private float speed = 10;
    private boolean active = true;
    private CollisionRect collisionRect;
    private Monster shooterMonster;

    public Bullet(float startX, float startY, float targetX, float targetY, int damage, boolean screenCoordinates) {
        sprite.setSize(20, 20);
        sprite.setPosition(startX, startY);

        float angle;
        if (screenCoordinates) {
            float worldTargetY = Gdx.graphics.getHeight() - targetY;
            angle = MathUtils.atan2(worldTargetY - startY, targetX - startX);
        } else {
            angle = MathUtils.atan2(targetY - startY, targetX - startX);
        }

        this.directionX = MathUtils.cos(angle) * speed;
        this.directionY = MathUtils.sin(angle) * speed;
        this.collisionRect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        this.damage = damage;
    }

    public void updatePosition() {
        if (!active) return;
        sprite.setX(sprite.getX() + directionX);
        sprite.setY(sprite.getY() + directionY);
        collisionRect.move(sprite.getX(), sprite.getY());
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
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

    public int getDamage() {
        return damage;
    }

    public Monster getShooterMonster() {
        return shooterMonster;
    }

    public void setShooterMonster(Monster shooterMonster) {
        this.shooterMonster = shooterMonster;
    }
}
