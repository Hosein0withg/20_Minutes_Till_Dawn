package com.tilldown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class TentacleMonster {
    public Texture texture = new Texture(GameAssetManager.tentacleMonster[0]);
    public Sprite sprite = new Sprite(texture);
    private float posX = 0;
    private float posY = 0;
    private CollisionRect rect;
    private static float spawnRate = 0;
    private int HP = 25;
    private float time = 0;
    private float speed = 0.5f;

    public TentacleMonster() {
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
        sprite.setSize((float) (texture.getWidth() * 1.5), (float) (texture.getHeight() * 1.5));
        rect = new CollisionRect(posX, posY, (float) (texture.getWidth() * 1.5), (float) (texture.getHeight() * 1.5));
    }

    public void moveMonster() {
        float playerX = Game.getCurrentUser().getCurrentHero().getPosX();
        float playerY = Game.getCurrentUser().getCurrentHero().getPosY();
        if (posX < playerX) {
            posX += speed;
        } else if (posX > playerX) {
            posX -= speed;
        }
        if (posY < playerY) {
            posY += speed;
        } else if (posY > playerY) {
            posY -= speed;
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

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }

    public static void setSpawnRate(float spawnRate) {
        TentacleMonster.spawnRate = spawnRate;
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
}
