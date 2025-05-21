package com.tilldown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.MenuControl.PreGameMenuController;
import com.tilldown.Main;

public class PreGameMenu implements Screen {
    private Stage stage;
    private final TextButton startGameButton;
    private final SelectBox selectHere;
    private final SelectBox selectWeapon;
    private final SelectBox selectDuration;
    public Table table;
    private final PreGameMenuController controller;

    public PreGameMenu(PreGameMenuController controller, Skin skin) {
        this.controller = controller;
        this.startGameButton = new TextButton("Play", skin);
        this.selectHere = new SelectBox(skin);
        this.selectWeapon = new SelectBox(skin);
        this.selectDuration = new SelectBox(skin);
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

        table.setFillParent(true);
        table.center();
        table.add(selectHere).width(400);
        table.row();
        table.add(selectWeapon).width(400);
        table.row();
        table.add(selectDuration).width(400);
        table.row();
        table.add(startGameButton).width(400);
        table.row();
        stage.addActor(table);
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
}
