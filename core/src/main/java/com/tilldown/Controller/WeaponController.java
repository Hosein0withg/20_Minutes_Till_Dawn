package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.tilldown.Main;
import com.tilldown.Model.Bullet;
import com.tilldown.Model.Game;
import com.tilldown.Model.TentacleMonster;
import com.tilldown.Model.Weapon;

import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController {
    private Weapon weapon;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private boolean isReloading = false;
    private GameController gameController;

    public WeaponController(Weapon weapon, GameController gameController) {
        this.weapon = weapon;
        this.gameController = gameController;
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
        float weaponX = weapon.getGunSprite().getX();
        float weaponY = weapon.getGunSprite().getY();

        if (Game.getCurrentUser().getCurrentHero().getAmmoLeft() > 0 && !isReloading) {
            bullets.add(new Bullet(weaponX, weaponY, x, y,
                Game.getCurrentUser().getCurrentHero().getCurrentGun().getDamage() * Game.getCurrentUser().getCurrentHero().getCurrentGun().getProjectile()));
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
            if (!b.isActive()) {
                iterator.remove();
                continue;
            }
            b.updatePosition();
            checkMonsterCollisions(b);
            b.getSprite().draw(Main.getBatch());

            if (b.getSprite().getX() < 0 || b.getSprite().getX() > Gdx.graphics.getWidth() ||
                b.getSprite().getY() < 0 || b.getSprite().getY() > Gdx.graphics.getHeight()) {
                iterator.remove();
            }
        }
    }

    private void checkMonsterCollisions(Bullet bullet) {
        for (TentacleMonster monster : gameController.getWorldController().monsters) {
            if (bullet.getCollisionRect().overlap(monster.getRect())) {
                monster.setHP(monster.getHP() - bullet.getDamage());
                bullet.deactivate();
                break;
            }
        }
    }

}
