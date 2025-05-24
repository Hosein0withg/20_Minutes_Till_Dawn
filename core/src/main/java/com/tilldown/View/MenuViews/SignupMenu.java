package com.tilldown.View.MenuViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.MenuControl.SignupMenuController;
import com.tilldown.Main;

public class SignupMenu implements Screen {
    private Stage stage;
    private final TextButton signupButton;
    private final TextField username;
    private final TextField password;
    private final TextField securityQuestion;
    private final TextField securityAnswer;
    public final Label errorLabel;
    public Table table;
    private final SignupMenuController controller;

    public SignupMenu(SignupMenuController controller, Skin skin) {
        this.controller = controller;
        this.signupButton = new TextButton("sign me up!", skin);
        this.username = new TextField("", skin);
        this.username.setMessageText("Enter your username");
        this.password = new TextField("", skin);
        this.password.setMessageText("Enter your password");
        this.securityQuestion = new TextField("", skin);
        this.securityQuestion.setMessageText("Enter your security question");
        this.securityAnswer = new TextField("", skin);
        this.securityAnswer.setMessageText("Enter your security answer");
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
        table.add(securityQuestion).width(600).height(80).pad(10);
        table.row();
        table.add(securityAnswer).width(600).height(80).pad(10);
        table.row();
        table.add(signupButton).width(400).height(120).pad(10);
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
        controller.handleSignupMenuButton(username.getText(), password.getText(), securityQuestion.getText(), securityAnswer.getText());
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
}
