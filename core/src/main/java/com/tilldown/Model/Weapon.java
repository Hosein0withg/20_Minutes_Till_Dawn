package com.tilldown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Weapon {
    private Texture gunTexture;
    private Sprite gunSprite;
    private int ammo;
    private int damage;
    private int reloadTime;
    private int projectile;
    private float offsetX = 20f;
    private float offsetY = -10f;

    public Weapon(int damage, int projectile, int reloadTime, int ammo) {
        switch (damage) {
            case 20:
                this.gunTexture = new Texture(GameAssetManager.getGameAssetManager().getRevolver());
                break;
            case 10:
                this.gunTexture = new Texture(GameAssetManager.getGameAssetManager().getShotgun());
                break;
            case 8:
                this.gunTexture = new Texture(GameAssetManager.getGameAssetManager().getSmg());
                break;
        }
        this.gunSprite = new Sprite(gunTexture);
        gunSprite.setSize(50, 50);
        this.ammo = ammo;
        this.damage = damage;
        this.projectile = projectile;
        this.reloadTime = reloadTime;
    }

    public void updatePosition(float playerX, float playerY) {
        gunSprite.setX(playerX + offsetX);
        gunSprite.setY(playerY + offsetY);
    }

    public void draw(SpriteBatch batch) {
        gunSprite.draw(batch);
    }

    public Sprite getGunSprite() {
        return gunSprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public Texture getGunTexture() {
        return gunTexture;
    }

    public int getDamage() {
        return damage;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public int getProjectile() {
        return projectile;
    }

    public void setGunTexture(Texture gunTexture) {
        this.gunTexture = gunTexture;
    }

    public void setGunSprite(Sprite gunSprite) {
        this.gunSprite = gunSprite;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    public void setProjectile(int projectile) {
        this.projectile = projectile;
    }
}
