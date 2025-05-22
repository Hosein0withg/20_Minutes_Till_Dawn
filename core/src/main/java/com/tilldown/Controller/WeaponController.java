package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tilldown.Main;
import com.tilldown.Model.Bullet;
import com.tilldown.Model.Game;
import com.tilldown.Model.Weapon;

import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController {
    private Weapon weapon;
    private ArrayList<Bullet> bullets = new ArrayList<>();

    public WeaponController(Weapon weapon) {
        this.weapon = weapon;
    }

    public void update() {
        weapon.getGunSprite().draw(Main.getBatch());
        updateBullets();
    }

    public void handleWeaponRotation(int x, int y) {
        Sprite weaponSprite = weapon.getGunSprite();

        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);

        weaponSprite.setRotation((float) (Math.PI - angle * MathUtils.radiansToDegrees));
    }

    public void handleWeaponShoot(int x, int y) {
        bullets.add(new Bullet(x, y));
        Game.getCurrentUser().getCurrentHero().setAmmoLeft(Game.getCurrentUser().getCurrentHero().getAmmoLeft() - 1);
    }

    public void updateBullets() {
        Iterator<Bullet> iterator = bullets.iterator();

        while (iterator.hasNext()) {
            Bullet b = iterator.next();
            b.getSprite().draw(Main.getBatch());

            Vector2 direction = new Vector2(
                Gdx.graphics.getWidth() / 2f - b.getX(),
                Gdx.graphics.getHeight() / 2f - b.getY()
            ).nor();

            b.getSprite().setX(b.getSprite().getX() - direction.x * 5);
            b.getSprite().setY(b.getSprite().getY() + direction.y * 5);

            if (b.getSprite().getX() < 0 || b.getSprite().getX() > Gdx.graphics.getWidth() ||
                b.getSprite().getY() < 0 || b.getSprite().getY() > Gdx.graphics.getHeight()) {
                iterator.remove();
            }
        }
    }

}
