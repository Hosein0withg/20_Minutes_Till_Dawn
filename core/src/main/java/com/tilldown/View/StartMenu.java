package com.tilldown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.StartMenuController;
import com.tilldown.Main;

public class StartMenu implements Screen {
    private Stage stage;
    private final TextButton signupButton;
    private final TextButton loginButton;
    private final TextButton guestButton;
    private final TextButton exitButton;
    private final Label gameTitle;
    public Table table;
    private final StartMenuController controller;

    public StartMenu(StartMenuController controller, Skin skin) {
        this.controller = controller;
        this.signupButton = new TextButton("signup", skin);
        this.loginButton = new TextButton("login", skin);
        this.guestButton = new TextButton("guest", skin);
        this.exitButton = new TextButton("exit", skin);
        this.gameTitle = new Label("20 MINUTES TILL DAWN", skin);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(gameTitle);
        table.row();
        table.add(signupButton).width(200).height(100);
        table.row();
        table.add(loginButton).width(200).height(100);
        table.row();
        table.add(guestButton).width(200).height(100);
        table.row();
        table.add(exitButton).width(200).height(100);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleStartMenuButtons();
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

    public TextButton getSignupButton() {
        return signupButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    public TextButton getGuestButton() {
        return guestButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }
}
