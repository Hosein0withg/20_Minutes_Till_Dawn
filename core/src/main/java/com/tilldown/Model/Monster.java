package com.tilldown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class Monster {
    public monsterName monsterName;
    public Texture texture;
    public Sprite sprite;
    private float posX = 0;
    private float posY = 0;
    private CollisionRect rect;
    private int HP;
    private float time = 0;
    private float speed;
    private float shootTimer = 0.0f;
    private boolean canShoot = false;

    public Monster(monsterName monsterName, int HP, float speed, String idle0, float sizeMultiplier) {
        this.monsterName = monsterName;
        texture = new Texture(idle0);
        sprite = new Sprite(texture);
        this.HP = HP;
        this.speed = speed;
        switch (MathUtils.random(1, 4)) {
            case 1: // left
                posX = 50;
                posY = (float) MathUtils.random(50, Gdx.graphics.getHeight() - 50);
                break;
            case 2: // top
                posX = (float) MathUtils.random(50, Gdx.graphics.getWidth() - 50);
                posY = Gdx.graphics.getHeight() - 50;
                break;
            case 3: // right
                posX = Gdx.graphics.getWidth() - 50;
                posY = (float) MathUtils.random(50, Gdx.graphics.getHeight() - 50);
                break;
            case 4: // down
                posX = (float) MathUtils.random(50, Gdx.graphics.getWidth() - 50);
                posY = 50;
                break;
        }
        sprite.setPosition(posX, posY);
        sprite.setSize((float) (texture.getWidth() * sizeMultiplier), (float) (texture.getHeight() * sizeMultiplier));
        rect = new CollisionRect(posX, posY, (float) (texture.getWidth() * sizeMultiplier), (float) (texture.getHeight() * sizeMultiplier));
        if (this.monsterName == com.tilldown.Model.monsterName.Eyebat) {
            this.canShoot = true;
        }
    }

    public void moveMonster() {
        float playerX = Game.getCurrentUser().getCurrentHero().getPosX();
        float playerY = Game.getCurrentUser().getCurrentHero().getPosY();
        if (Math.abs(playerX - posX) > 5) {
            if (posX < playerX) {
                posX += speed;
            } else if (posX > playerX) {
                posX -= speed;
            }
        }
        if (Math.abs(playerY - posY) > 5) {
            if (posY < playerY) {
                posY += speed;
            } else if (posY > playerY) {
                posY -= speed;
            }
        }
        sprite.setPosition(posX, posY);
        rect.move(posX, posY);
        time += Gdx.graphics.getDeltaTime();
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

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getShootTimer() {
        return shootTimer;
    }

    public void setShootTimer(float shootTimer) {
        this.shootTimer = shootTimer;
    }

    public boolean isCanShoot() {
        return canShoot;
    }
}
