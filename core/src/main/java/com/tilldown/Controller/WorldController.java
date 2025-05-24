package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.Model.TentacleMonster;

import java.util.ArrayList;

public class WorldController {
    private PlayerController playerController;
    private final Texture backgroundTexture;
    private ArrayList<TentacleMonster> monsters = new ArrayList<>();
    private float monsterSpawnTimer = 0;
    private Animation<Texture> tentacleAnimation;

    public WorldController(PlayerController playerController) {
        this.backgroundTexture = new Texture("background.png");
        this.playerController = playerController;
        this.tentacleAnimation = GameAssetManager.getGameAssetManager().getTentacle_idle_animation();
    }

    public void update(float delta) {
        Main.getBatch().draw(backgroundTexture, 0, 0);
        monsterSpawnTimer += delta;
        if (monsterSpawnTimer >= 3f) {
            int monstersToSpawn = (int) Math.floor(Game.getCurrentUser().gameTimePassed / 30f);
            for (int i = 0; i < monstersToSpawn; i++) {
                monsters.add(new TentacleMonster());
            }
            monsterSpawnTimer = 0;
        }
        for (TentacleMonster monster : monsters) {
            monster.setTime(monster.getTime() + delta);
            monster.getSprite().setRegion(tentacleAnimation.getKeyFrame(monster.getTime()));
            monster.moveMonster();
            monster.getSprite().draw(Main.getBatch());
            if (monster.getHP() <= 0) {
                monsters.remove(monster);
            }
        }
    }

}
