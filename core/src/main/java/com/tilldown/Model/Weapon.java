package com.tilldown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    private Texture gunTexture;
    private Sprite gunSprite;
    private int ammo;
    private int damage;
    private int reloadTime;
    private int projectile;

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
        gunSprite.setX((float) Gdx.graphics.getWidth() / 2);
        gunSprite.setY((float) Gdx.graphics.getHeight() / 2);
        gunSprite.setSize(50, 50);
        this.ammo = ammo;
        this.damage = damage;
        this.projectile = projectile;
        this.reloadTime = reloadTime;
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
}
