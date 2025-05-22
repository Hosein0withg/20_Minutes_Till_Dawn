package com.tilldown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;
import com.tilldown.Main;

import java.util.Random;

public class Player {
    private Texture playerTexture;
    private Sprite playerSprite;
    private float posX = 0;
    private float posY = 0;
    private CollisionRect rect;
    private float time = 0;
    private float HP = 100;
    private float speed = 5;
    private int XP = 0;
    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;
    private final Animation<Texture> character_idle_frames;
    private int kill = 0;
    private int level = 1;
    private int ammoLeft;
    private Weapon currentGun;


    public Player(int HP, int speed, Animation<Texture> character_idle_frames, String idle0Address) {
        this.playerTexture = new Texture(idle0Address);
        this.playerSprite = new Sprite(playerTexture);
        playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        playerSprite.setSize(playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
        rect = new CollisionRect((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight(),
            playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
        this.HP = HP;
        this.speed = speed;
        this.character_idle_frames = character_idle_frames;
        this.currentGun = Game.guns.get(0);
        this.ammoLeft = this.currentGun.getAmmo();
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
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

    public float getHP() {
        return HP;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }

    public Animation<Texture> getCharacter_idle_frames() {
        return character_idle_frames;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getKill() {
        return kill;
    }

    public int getLevel() {
        return level;
    }

    public int getAmmoLeft() {
        return ammoLeft;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAmmoLeft(int ammoLeft) {
        this.ammoLeft = ammoLeft;
    }

    public Weapon getCurrentGun() {
        return currentGun;
    }

    public void setCurrentGun(Weapon currentGun) {
        this.currentGun = currentGun;
    }

    public int calculateNextLevelXP() {
        return (20 * level);
    }

    public String setAbility() {
        switch (new Random().nextInt(5)) {
            case 0:
                if (!Game.getCurrentUser().gainedAbilities.contains("Vitality"))
                    Game.getCurrentUser().gainedAbilities.add("Vitality");
                Game.getCurrentUser().getCurrentHero().setHP(Game.getCurrentUser().getCurrentHero().getHP() + 1);
                return "Vitality";
            case 1:
                if (!Game.getCurrentUser().gainedAbilities.contains("Damager"))
                    Game.getCurrentUser().gainedAbilities.add("Damager");
                boostGunDamage();
                return "Damager";
            case 2:
                if (!Game.getCurrentUser().gainedAbilities.contains("Procrease"))
                    Game.getCurrentUser().gainedAbilities.add("Procrease");
                Game.getCurrentUser().getCurrentHero().getCurrentGun().setProjectile(Game.getCurrentUser().getCurrentHero().getCurrentGun().getProjectile() + 1);
                return "Procrease";
            case 3:
                if (!Game.getCurrentUser().gainedAbilities.contains("Amocrease"))
                    Game.getCurrentUser().gainedAbilities.add("Amocrease");
                Game.getCurrentUser().getCurrentHero().getCurrentGun().setAmmo(Game.getCurrentUser().getCurrentHero().getCurrentGun().getAmmo() + 5);
                return "Amocrease";
            case 4:
                if (!Game.getCurrentUser().gainedAbilities.contains("Speedy"))
                    Game.getCurrentUser().gainedAbilities.add("Speedy");
                boostPlayerSpeed();
                return "Speedy";
        }
        return "";
    }

    private void boostGunDamage() {
        Weapon weapon = Game.getCurrentUser().getCurrentHero().getCurrentGun();
        int originalDamage = weapon.getDamage();
        weapon.setDamage((int) (originalDamage * 1.25));

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                weapon.setDamage(originalDamage);
            }
        }, 10);
    }

    private void boostPlayerSpeed() {
        Player player = Game.getCurrentUser().getCurrentHero();
        player.setSpeed(player.getSpeed() * 2);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                player.setSpeed(player.getSpeed() / 2);
            }
        }, 10);
    }
}
