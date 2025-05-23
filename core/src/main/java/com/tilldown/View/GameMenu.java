package com.tilldown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.MenuControl.GameMenuController;
import com.tilldown.Main;

public class GameMenu implements Screen {
    private Stage stage;
    private final TextButton profileButton;
    private final TextButton settingsButton;
    private final TextButton scoreboardButton;
    private final TextButton preGameButton;
    private final TextButton hintButton;
    private final TextButton backButton;
    public Table table;
    private final GameMenuController controller;

    public GameMenu(GameMenuController controller, Skin skin) {
        this.controller = controller;
        this.profileButton = new TextButton("Profile", skin);
        this.settingsButton = new TextButton("Setting", skin);
        this.scoreboardButton = new TextButton("Scoreboard", skin);
        this.preGameButton = new TextButton("Pre Game", skin);
        this.hintButton = new TextButton("Hint", skin);
        this.backButton = new TextButton("Logout", skin);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(preGameButton).width(400);
        table.row();
        table.add(profileButton).width(400);
        table.row();
        table.add(settingsButton).width(400);
        table.row();
        table.add(scoreboardButton).width(400);
        table.row();
        table.add(hintButton).width(400);
        table.row();
        table.add(backButton).width(400);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleGameMenuButtons();
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

    public TextButton getHintButton() {
        return hintButton;
    }

    public TextButton getPreGameButton() {
        return preGameButton;
    }

    public TextButton getScoreboardButton() {
        return scoreboardButton;
    }

    public TextButton getSettingsButton() {
        return settingsButton;
    }

    public TextButton getProfileButton() {
        return profileButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
