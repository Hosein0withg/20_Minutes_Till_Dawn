package com.tilldown.View.MenuViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.MenuControl.PreGameMenuController;
import com.tilldown.Main;

public class PreGameMenu implements Screen {
    private Stage stage;
    private final TextButton startGameButton;
    private final TextButton backButton;
    private final SelectBox selectHere;
    private final SelectBox selectWeapon;
    private final SelectBox selectDuration;
    private final Label hero;
    private final Label gun;
    private final Label duration;
    public Table table;
    private final PreGameMenuController controller;

    public PreGameMenu(PreGameMenuController controller, Skin skin) {
        this.controller = controller;
        this.startGameButton = new TextButton("Play", skin);
        this.backButton = new TextButton("Back", skin);
        this.selectHere = new SelectBox(skin);
        this.selectWeapon = new SelectBox(skin);
        this.selectDuration = new SelectBox(skin);
        this.hero = new Label("Hero:", skin);
        this.gun = new Label("Gun:", skin);
        this.duration = new Label("Game Duration:", skin);
        this.table = new Table();
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Array<String> hero = new Array<>();
        hero.add("Shana");
        hero.add("Diamond");
        hero.add("Scarlet");
        hero.add("Lilith");
        hero.add("Dasher");
        selectHere.setItems(hero);

        Array<String> guns = new Array<>();
        guns.add("Revolver");
        guns.add("Shotgun");
        guns.add("SMG");
        selectWeapon.setItems(guns);

        Array<String> durations = new Array<>();
        durations.add("2");
        durations.add("5");
        durations.add("10");
        durations.add("20");
        selectDuration.setItems(durations);

        table.setFillParent(true);
        table.center();
        table.add(selectHere).width(400);
        table.row();
        table.add(selectWeapon).width(400);
        table.row();
        table.add(selectDuration).width(400);
        table.row();
        table.add(startGameButton).width(400).pad(10);
        table.row();
        table.add(backButton).width(400).pad(50);
        stage.addActor(table);

        this.hero.setPosition(600, 765);
        gun.setPosition(600, 680);
        duration.setPosition(524, 595);
        stage.addActor(this.hero);
        stage.addActor(gun);
        stage.addActor(duration);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handlePreGameMenuButtons();
        controller.handleHeroSelection();
        controller.handleGunSelection();
        controller.handleTimeSelection();
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

    public TextButton getStartGameButton() {
        return startGameButton;
    }

    public String getSelectedHero() {
        return selectHere.getSelected().toString();
    }

    public String getSelectedGun() {
        return selectWeapon.getSelected().toString();
    }

    public String getSelectedDuration() {
        return selectDuration.getSelected().toString();
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
