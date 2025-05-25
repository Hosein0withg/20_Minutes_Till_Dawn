package com.tilldown.View.MenuViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.Controller.MenuControl.SettingMenuController;

public class SettingMenu implements Screen {
    private final SettingMenuController musicManager = SettingMenuController.getInstance();
    private final Stage stage;
    private final SelectBox<String> selectMusic;
    private final Slider volumeSlider;
    private final TextButton stopButton;
    private final TextButton backButton;
    private TextButton toggleButton;
    private TextButton toggleButton2;
    private TextButton toggleButton3;
    private final Table table;

    public SettingMenu(Skin skin) {
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.selectMusic = new SelectBox<>(skin);
        this.stopButton = new TextButton("Stop", skin);
        this.volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        this.backButton = new TextButton("Back", skin);
        toggleButton = new TextButton("Auto Reload: OFF", GameAssetManager.getGameAssetManager().getSkin());
        toggleButton2 = new TextButton("Controller: W-A-S-D", GameAssetManager.getGameAssetManager().getSkin());
        toggleButton3 = new TextButton("SFX: ON", GameAssetManager.getGameAssetManager().getSkin());
        volumeSlider.setValue(0.5f);
        this.table = new Table();
        musicManager.setView(this);

        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                musicManager.setVolume(volumeSlider.getValue());
            }
        });

        selectMusic.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int selectedIndex = selectMusic.getSelectedIndex();
                musicManager.playMusic(GameAssetManager.musicOptions[selectedIndex]);
            }
        });

        stopButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                musicManager.stopMusic();
            }
        });

        toggleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Game.getCurrentUser().setAutoReload(!Game.getCurrentUser().isAutoReload());
                toggleButton.setText("Auto Reload: " + (Game.getCurrentUser().isAutoReload() ? "ON" : "OFF"));
            }
        });

        toggleButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Game.getCurrentUser().setNumberController(!Game.getCurrentUser().isNumberController());
                if (Game.getCurrentUser().isNumberController()) toggleButton2.setText("Controller: 8-4-2-6");
                else toggleButton2.setText("Controller: W-A-S-D");
            }
        });

        toggleButton3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Game.getCurrentUser().setSfx(!Game.getCurrentUser().isSfx());
                if (Game.getCurrentUser().isSfx()) toggleButton3.setText("SFX: ON");
                else toggleButton3.setText("SFX: OFF");
            }
        });
    }

    @Override
    public void show() {
        table.setFillParent(true);

        Array<String> musics = new Array<>();
        musics.add("The Last of Us (Gustavo Santaolalla)");
        musics.add("Seven Nation Army (The White Stripes)");
        musics.add("Where Is My Mind? (Pixies)");

        selectMusic.setItems(musics);
        table.add(selectMusic).width(600).pad(10);
        table.row();
        table.add(stopButton).width(300).pad(10);
        table.row();
        table.add(volumeSlider).width(300).pad(10);
        table.row();
        table.add(toggleButton).width(550).pad(10);
        table.row();
        table.add(toggleButton2).width(550).pad(10);
        table.row();
        table.add(toggleButton3).width(550).pad(10);
        table.row();
        table.add(backButton).width(300).pad(10);
        stage.addActor(table);
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
