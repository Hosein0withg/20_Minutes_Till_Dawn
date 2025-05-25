package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Timer;
import com.tilldown.Main;
import com.tilldown.Model.*;

import java.util.ArrayList;
import java.util.Iterator;

public class WorldController {
    private PlayerController playerController;
    private final Texture backgroundTexture;
    public ArrayList<Monster> monsters = new ArrayList<>();
    private float tentacleSpawnTimer = 0;
    private float eyebatSpawnTimer = 0;
    private float elderDashTimer = 0;
    private ArrayList<Bullet> eyebatBullets = new ArrayList<>();
    private final Animation<Texture> tentacleAnimation;
    private final Animation<Texture> eyebatAnimation;
    private final Animation<Texture> elderAnimation;
    private boolean isBossSpawned = false;

    public WorldController(PlayerController playerController) {
        this.backgroundTexture = new Texture("background.png");
        this.playerController = playerController;
        this.tentacleAnimation = GameAssetManager.getGameAssetManager().getTentacle_idle_animation();
        this.eyebatAnimation = GameAssetManager.getGameAssetManager().getEyebat_idle_animation();
        this.elderAnimation = GameAssetManager.getGameAssetManager().getElder_idle_animation();
    }

    public void update(float delta) {
        Main.getBatch().draw(backgroundTexture, 0, 0);

        tentacleSpawnTimer += delta;
        eyebatSpawnTimer += delta;
        elderDashTimer += delta;

        if (tentacleSpawnTimer >= 3f) {
            int tentaclesToSpawn = (int) Math.floor(Game.gameTimePassed / 30f);
            for (int i = 0; i < tentaclesToSpawn; i++) {
                monsters.add(new Monster(monsterName.TentacleMonster, 25, 0.5f, GameAssetManager.tentacleMonster[0], 1.2f));
            }
            tentacleSpawnTimer = 0;
        }
        if (Game.gameTimePassed >= (Game.gameDuration * 15) && eyebatSpawnTimer >= 10f) {
            int eyebatsToSpawn = (int) Math.floor((double) (4 * Game.gameTimePassed - (Game.gameDuration * 60) + 30) / 30);
            for (int i = 0; i < eyebatsToSpawn; i++) {
                monsters.add(new Monster(monsterName.Eyebat, 50, 0.6f, GameAssetManager.eyebat[0], 1f));
            }
            eyebatSpawnTimer = 0;
        }
        if (!isBossSpawned && Game.gameTimePassed >= (Game.gameDuration * 30)) {
            spawnBoss();
        }

        for (int i = 0; i < monsters.size(); i++) {
            Monster monster = monsters.get(i);
            monster.setTime(monster.getTime() + delta);
            switch (monster.monsterName) {
                case TentacleMonster:
                    monster.getSprite().setRegion(tentacleAnimation.getKeyFrame(monster.getTime(), true));
                    break;
                case Eyebat:
                    monster.getSprite().setRegion(eyebatAnimation.getKeyFrame(monster.getTime(), true));
                    break;
                case Elder:
                    monster.getSprite().setRegion(elderAnimation.getKeyFrame(monster.getTime(), true));
                    break;
            }
            if (monster.monsterName == monsterName.Elder && elderDashTimer >= 5f) {
                elderDashTimer = 0;
                monster.setSpeed(2.1f);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        monster.setSpeed(0.7f);
                    }
                }, 1.5f);
            }
            if (monster.isCanShoot()) {
                monster.setShootTimer(monster.getShootTimer() + delta);
                if (monster.getShootTimer() >= 3f) {
                    monster.setShootTimer(0);
                    Bullet bullet = new Bullet(monster.getPosX(), monster.getPosY(), App.getCurrentHero().getPosX(), App.getCurrentHero().getPosY(), 1, false);
                    bullet.setShooterMonster(monster);
                    eyebatBullets.add(bullet);

                }
            }
            monster.moveMonster();
            monster.getSprite().draw(Main.getBatch());
            if (monster.getHP() <= 0) {
                Game.seedsOnMap.add(new Seed(monster.getPosX(), monster.getPosY()));
                monsters.remove(monster);
                Game.getCurrentUser().getCurrentHero().setKill(Game.getCurrentUser().getCurrentHero().getKill() + 1);
                i--;
            }
        }

        updateEyebatBullets();
    }

    public void updateEyebatBullets() {
        Iterator<Bullet> iterator = eyebatBullets.iterator();

        while (iterator.hasNext()) {
            Bullet b = iterator.next();
            if (!b.isActive()) {
                iterator.remove();
                continue;
            }
            b.updatePosition();
            checkIfBulletHitPlayer(b);
            b.getSprite().draw(Main.getBatch());

            if (b.getSprite().getX() < 0 || b.getSprite().getX() > Gdx.graphics.getWidth() ||
                b.getSprite().getY() < 0 || b.getSprite().getY() > Gdx.graphics.getHeight()) {
                iterator.remove();
            }
        }
    }

    private void checkIfBulletHitPlayer(Bullet bullet) {
        if (bullet.getCollisionRect().overlap(App.getCurrentHero().getRect())) {
            App.getCurrentHero().gotDamaged();
            bullet.deactivate();
        }
        for (Monster monster : playerController.getGameController().getWorldController().monsters) {
            if (bullet.getCollisionRect().overlap(monster.getRect()) && !bullet.getShooterMonster().equals(monster)) {
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

    public void spawnBoss() {
        isBossSpawned = true;
        monsters.add(new Monster(monsterName.Elder, 400, 0.7f, GameAssetManager.elder[0], 3f));
    }

}
