package com.tilldown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.MenuControl.ChangeUsernameController;
import com.tilldown.Main;

public class ChangeUsername implements Screen {
    private Stage stage;
    private final TextButton changeUsername;
    public final TextField username;
    public final Label errorLabel;
    public Table table;
    private final ChangeUsernameController controller;

    public ChangeUsername(ChangeUsernameController controller, Skin skin) {
        this.controller = controller;
        this.username = new TextField("", skin);
        this.username.setMessageText("Enter a new username");
        this.changeUsername = new TextButton("Change", skin);
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
        table.add(changeUsername).width(600).height(80).pad(10);
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
        controller.handleChangeUsernameMenuButton();
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

    public TextButton getChangeUsername() {
        return changeUsername;
    }
}
