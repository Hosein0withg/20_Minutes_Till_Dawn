package com.tilldown.Controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tilldown.Main;
import com.tilldown.Model.*;

import java.util.ArrayList;

public class WorldController {
    private PlayerController playerController;
    private final Texture backgroundTexture;
    public ArrayList<Monster> monsters = new ArrayList<>();
    private float tentacleSpawnTimer = 0;
    private float eyebatSpawnTimer = 0;
    private final Animation<Texture> tentacleAnimation;
    private final Animation<Texture> eyebatAnimation;
    private final Animation<Texture> elderAnimation;

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

        if (tentacleSpawnTimer >= 3f) {
            int tentaclesToSpawn = (int) Math.floor(Game.gameTimePassed / 30f);
            for (int i = 0; i < tentaclesToSpawn; i++) {
                monsters.add(new Monster(monsterName.TentacleMonster, 25, 0.5f, GameAssetManager.tentacleMonster[0]));
            }
            tentacleSpawnTimer = 0;
        }
        if (Game.gameTimePassed >= (Game.gameDuration * 15) && eyebatSpawnTimer >= 10f) {
            int eyebatsToSpawn = (int) Math.floor((double) (4 * Game.gameTimePassed - (Game.gameDuration * 60) + 30) / 30);
            for (int i = 0; i < eyebatsToSpawn; i++) {
                monsters.add(new Monster(monsterName.Eyebat, 50, 0.6f, GameAssetManager.eyebat[0]));
            }
            eyebatSpawnTimer = 0;
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
            monster.moveMonster();
            monster.getSprite().draw(Main.getBatch());
            if (monster.getHP() <= 0) {
                Game.seedsOnMap.add(new Seed(monster.getPosX(), monster.getPosY()));
                monsters.remove(monster);
                Game.getCurrentUser().getCurrentHero().setKill(Game.getCurrentUser().getCurrentHero().getKill() + 1);
                i--;
            }
        }
    }

}
