package com.tilldown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.MenuControl.ForgetPasswordController;
import com.tilldown.Main;

public class ForgetPassword implements Screen {
    private Stage stage;
    private final TextButton submitUsernameButton;
    private final TextButton submitAnswerButton;
    private final TextButton submitPasswordButton;
    private final TextField username;
    public final TextField answer;
    public final TextField password;
    public final Label errorLabel;
    public Label questionLabel;
    public Table table;
    private final ForgetPasswordController controller;

    public ForgetPassword(ForgetPasswordController controller, Skin skin) {
        this.controller = controller;
        this.submitUsernameButton = new TextButton("Submit Username", skin);
        this.submitAnswerButton = new TextButton("Submit Answer", skin);
        this.submitPasswordButton = new TextButton("Submit Password", skin);

        this.username = new TextField("", skin);
        this.username.setMessageText("Enter your username");

        this.password = new TextField("", skin);
        this.password.setMessageText("Enter your new password");
        this.password.setVisible(false);

        this.answer = new TextField("", skin);
        this.answer.setVisible(false);

        this.questionLabel = new Label("", skin);
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
        table.add(submitUsernameButton).width(500).height(120).pad(10);
        table.row();
        table.add(questionLabel).pad(10);
        table.row();
        answer.setVisible(false);
        table.add(answer).width(600).height(80).pad(10);
        table.row();
        submitAnswerButton.setVisible(false);
        table.add(submitAnswerButton).width(500).height(120).pad(10);
        table.row();
        password.setVisible(false);
        table.add(password).width(600).height(80).pad(10);
        table.row();
        submitPasswordButton.setVisible(false);
        table.add(submitPasswordButton).width(500).height(120).pad(10);
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
        controller.handleSubmitUsernameButton(username.getText());
        controller.handleSubmitAnswerButton(username.getText(), answer.getText());
        controller.handleSubmitPasswordButton(username.getText(), password.getText());

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

    public TextButton getSubmitUsernameButton() {
        return submitUsernameButton;
    }

    public TextButton getSubmitAnswerButton() {
        return submitAnswerButton;
    }

    public TextButton getSubmitPasswordButton() {
        return submitPasswordButton;
    }
}
