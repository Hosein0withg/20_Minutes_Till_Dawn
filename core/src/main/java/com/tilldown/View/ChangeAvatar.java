package com.tilldown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.ChangeAvatarController;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;

public class ChangeAvatar implements Screen {
    private Stage stage;
    private Table table;
    private ImageButton avatar1, avatar2, avatar3, avatar4;
    private TextButton customAvatarButton, backButton;
    private final ChangeAvatarController controller;

    public ChangeAvatar(ChangeAvatarController controller, Skin skin) {
        this.controller = controller;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        avatar1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(GameAssetManager.avatarOptions[0]))));
        avatar2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(GameAssetManager.avatarOptions[1]))));
        avatar3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(GameAssetManager.avatarOptions[2]))));
        avatar4 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(GameAssetManager.avatarOptions[3]))));

        customAvatarButton = new TextButton("Upload Avatar", skin);
        backButton = new TextButton("Back", skin);

        avatar1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.getCurrentPlayer().setAvatar(new Texture(GameAssetManager.avatarOptions[0]));
            }
        });

        avatar2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.getCurrentPlayer().setAvatar(new Texture(GameAssetManager.avatarOptions[1]));
            }
        });

        avatar3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.getCurrentPlayer().setAvatar(new Texture(GameAssetManager.avatarOptions[2]));
            }
        });

        avatar4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.getCurrentPlayer().setAvatar(new Texture(GameAssetManager.avatarOptions[3]));
            }
        });

        customAvatarButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.selectCustomAvatar();
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleBackButton();
            }
        });

        table = new Table();
        table.setFillParent(true);
        table.add(avatar1).width(100).height(100).pad(10);
        table.row();
        table.add(avatar2).width(100).height(100).pad(10);
        table.row();
        table.add(avatar3).width(100).height(100).pad(10);
        table.row();
        table.add(avatar4).width(100).height(100).pad(10);
        table.row();
        table.add(customAvatarButton).pad(10);
        table.row();
        table.add(backButton).pad(10);
        stage.addActor(table);

        controller.setView(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();
    }

    public TextButton getBackButton() {
        return backButton;
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
