package com.tilldown.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tilldown.Main;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.Model.TentacleMonster;

public class WorldController {
    private PlayerController playerController;
    private Texture backgroundTexture;
    private float backgroundX = 0;
    private float backgroundY = 0;
    private TentacleMonster tentacleMonster;

    public WorldController(PlayerController playerController) {
        this.backgroundTexture = new Texture("background.png");
        this.playerController = playerController;
        this.tentacleMonster = new TentacleMonster();
    }

    public void update() {
        backgroundX = playerController.getPlayer().getPosX();
        backgroundY = playerController.getPlayer().getPosY();
        Main.getBatch().draw(backgroundTexture, backgroundX, backgroundY);
        tentacleMonster.getSprite().draw(Main.getBatch());
        tentacleMonster.setPosY(tentacleMonster.getPosY() + 1);
        idleAnimation();
    }

    public void idleAnimation() {
        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getTentacle_idle_animation();

        tentacleMonster.getSprite().setRegion(animation.getKeyFrame(tentacleMonster.getTime()));

        if (!animation.isAnimationFinished(tentacleMonster.getTime())) {
            tentacleMonster.setTime(tentacleMonster.getTime() + Gdx.graphics.getDeltaTime());
        } else {
            tentacleMonster.setTime(0);
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

}
