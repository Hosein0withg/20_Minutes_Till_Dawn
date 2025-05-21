package com.tilldown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.MenuControl.LoginMenuController;
import com.tilldown.Main;

public class LoginMenu implements Screen {
    private Stage stage;
    private final TextButton loginButton;
    private final TextButton forgetButton;
    private final TextField username;
    private final TextField password;
    public final Label errorLabel;
    public Table table;
    private final LoginMenuController controller;

    public LoginMenu(LoginMenuController controller, Skin skin) {
        this.controller = controller;
        this.loginButton = new TextButton("log me in!", skin);
        this.forgetButton = new TextButton("forget password", skin);
        this.username = new TextField("", skin);
        this.username.setMessageText("Enter your username");
        this.password = new TextField("", skin);
        this.password.setMessageText("Enter your password");
        this.errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);

        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(username).width(600).height(80).pad(10);
        table.row();
        table.add(password).width(600).height(80).pad(10);
        table.row();
        table.add(loginButton).width(300).height(120).pad(10);
        table.row();
        table.add(forgetButton).width(500).height(120).pad(10);
        table.row();
        table.add(errorLabel).pad(10);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleLoginMenuButton(username.getText(), password.getText());
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

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getForgetButton() {
        return forgetButton;
    }


}
