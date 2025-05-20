package com.tilldown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.Controller.MusicMenuController;

public class MusicMenu implements Screen {
    private final MusicMenuController musicManager = MusicMenuController.getInstance();
    private final Stage stage;
    private final TextButton track1;
    private final TextButton track2;
    private final TextButton track3;
    private final Slider volumeSlider;
    private final TextButton stopButton;
    private final TextButton backButton;
    private final Table table;

    public MusicMenu(Skin skin) {
        this.track1 = new TextButton("Hedwig's Theme - John Williams", skin);
        this.track2 = new TextButton("Sur le fil - Yann Tiersen", skin);
        this.track3 = new TextButton("Mission Impossible - Adam Clayton & Larry Mullen", skin);
        this.stopButton = new TextButton("Stop", skin);
        this.volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        this.backButton = new TextButton("Back", skin);
        volumeSlider.setValue(0.5f);
        stage = new Stage(new ScreenViewport());
        this.table = new Table();
        Gdx.input.setInputProcessor(stage);
        musicManager.setView(this);

        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                musicManager.setVolume(volumeSlider.getValue());
            }
        });

        track1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                musicManager.playMusic(GameAssetManager.musicOptions[0]);
            }
        });

        track2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                musicManager.playMusic(GameAssetManager.musicOptions[1]);
            }
        });

        track3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                musicManager.playMusic(GameAssetManager.musicOptions[2]);
            }
        });

        stopButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                musicManager.stopMusic();
            }
        });

        table.setFillParent(true);
        table.add(track1).width(1500).pad(10);
        table.row();
        table.add(track2).width(1500).pad(10);
        table.row();
        table.add(track3).width(1500).pad(10);
        table.row();
        table.add(stopButton).width(1500).pad(10);
        table.row();
        table.add(volumeSlider).width(500).pad(10);
        table.row();
        table.add(backButton).width(300).pad(10);
        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        musicManager.handleMusicButtons();
    }

    @Override
    public void resize(int i, int i1) {

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
        stage.dispose();
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
