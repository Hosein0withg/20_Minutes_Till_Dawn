package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.tilldown.Main;
import com.tilldown.Model.*;

import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController {
    private final Weapon weapon;
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private boolean isReloading = false;
    private final GameController gameController;

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
        float weaponX = weapon.getGunSprite().getX();
        float weaponY = weapon.getGunSprite().getY();
        float angle = MathUtils.atan2(Gdx.graphics.getHeight() - y - weaponY, (float) x - weaponX);

        weapon.getGunSprite().setRotation(angle * MathUtils.radiansToDegrees);
    }

    public void handleWeaponShoot(int x, int y) {
        float weaponX = weapon.getGunSprite().getX();
        float weaponY = weapon.getGunSprite().getY();

        if (Game.getCurrentUser().getCurrentHero().getAmmoLeft() > 0 && !isReloading) {
            bullets.add(new Bullet(weaponX, weaponY, x, y,
                Game.getCurrentUser().getCurrentHero().getCurrentGun().getDamage() * Game.getCurrentUser().getCurrentHero().getCurrentGun().getProjectile(), true, 10));
            Game.getCurrentUser().getCurrentHero().setAmmoLeft(Game.getCurrentUser().getCurrentHero().getAmmoLeft() - 1);
            if (Game.getCurrentUser().isSfx())
                GameAssetManager.getGameAssetManager().getGunShotSound().play(1.0f);
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
            checkIfBulletHitEnemy(b);
            b.getSprite().draw(Main.getBatch());

            if (b.getSprite().getX() < 0 || b.getSprite().getX() > Gdx.graphics.getWidth() ||
                b.getSprite().getY() < 0 || b.getSprite().getY() > Gdx.graphics.getHeight()) {
                iterator.remove();
            }
        }
    }

    private void checkIfBulletHitEnemy(Bullet bullet) {
        for (Monster monster : gameController.getWorldController().monsters) {
            if (bullet.getCollisionRect().overlap(monster.getRect())) {
                monster.setHP(monster.getHP() - bullet.getDamage());
                bullet.deactivate();
                break;
            }
        }
        for (Tree tree : Game.trees) {
            if (bullet.getCollisionRect().overlap(tree.getRect())) {
                bullet.deactivate();
                break;
            }
        }
    }

}
