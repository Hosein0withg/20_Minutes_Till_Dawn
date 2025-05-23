package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.tilldown.Main;
import com.tilldown.Model.Bullet;
import com.tilldown.Model.Game;
import com.tilldown.Model.Weapon;

import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController {
    private Weapon weapon;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private boolean isReloading = false;

    public WeaponController(Weapon weapon) {
        this.weapon = weapon;
    }

    public void update() {
        weapon.getGunSprite().draw(Main.getBatch());
        updateBullets();
    }

    public void updateWeaponPosition(float playerX, float playerY) {
        weapon.updatePosition(playerX, playerY);
    }

    public void handleWeaponRotation(int x, int y) {
        float worldX = x;
        float worldY = Gdx.graphics.getHeight() - y;

        float weaponX = weapon.getGunSprite().getX();
        float weaponY = weapon.getGunSprite().getY();
        float angle = MathUtils.atan2(worldY - weaponY, worldX - weaponX);

        weapon.getGunSprite().setRotation(angle * MathUtils.radiansToDegrees);
    }

    public void handleWeaponShoot(int x, int y) {
        //float targetX = Gdx.graphics.getWidth() - x;
        //float targetY = Gdx.graphics.getHeight() - y;
        float weaponX = weapon.getGunSprite().getX();
        float weaponY = weapon.getGunSprite().getY();

        if (Game.getCurrentUser().getCurrentHero().getAmmoLeft() > 0 && !isReloading) {
            bullets.add(new Bullet(weaponX, weaponY, x, y));
            Game.getCurrentUser().getCurrentHero().setAmmoLeft(Game.getCurrentUser().getCurrentHero().getAmmoLeft() - 1);
        }
        if (Game.getCurrentUser().getCurrentHero().getAmmoLeft() <= 0 && Game.getCurrentUser().isAutoReload()) {
            reloadGun();
        }
    }

    public void reloadGun() {
        isReloading = true;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Game.getCurrentUser().getCurrentHero().setAmmoLeft(weapon.getAmmo());
                isReloading = false;
            }
        }, weapon.getReloadTime());
    }

    public void updateBullets() {
        Iterator<Bullet> iterator = bullets.iterator();

        while (iterator.hasNext()) {
            Bullet b = iterator.next();
            b.updatePosition();
            b.getSprite().draw(Main.getBatch());

            if (b.getSprite().getX() < 0 || b.getSprite().getX() > Gdx.graphics.getWidth() ||
                b.getSprite().getY() < 0 || b.getSprite().getY() > Gdx.graphics.getHeight()) {
                iterator.remove();
            }
        }
    }

}
