package com.tilldown.View.MenuViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.MenuControl.ScoreboardMenuController;
import com.tilldown.Main;
import com.tilldown.Model.App;
import com.tilldown.Model.User;

import java.util.ArrayList;

public class ScoreboardMenu implements Screen {
    private Stage stage;
    private final TextButton sortByUsername;
    private final TextButton sortByScore;
    private final TextButton sortByKill;
    private final TextButton sortByLivedTime;
    private final TextButton backButton;
    private final Label usenameLabel;
    private final Label scoreLabel;
    private final Label killLabel;
    private final Label livedTimeLabel;
    public Table table;
    public Table scoreboard;
    private ScoreboardMenuController controller;
    private Skin skin;
    public ArrayList<User> topUsers;

    public ScoreboardMenu(ScoreboardMenuController controller, Skin skin) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.sortByUsername = new TextButton("Sort By Username", skin);
        this.sortByScore = new TextButton("Sort By Score", skin);
        this.sortByKill = new TextButton("Sort By Kill", skin);
        this.sortByLivedTime = new TextButton("Sort By LivedTime", skin);
        this.backButton = new TextButton("Back", skin);
        this.usenameLabel = new Label("Username", skin);
        this.scoreLabel = new Label("Score", skin);
        this.killLabel = new Label("Kill", skin);
        this.livedTimeLabel = new Label("Most Lived Time", skin);
        this.table = new Table();
        this.scoreboard = new Table();
        this.controller = controller;
        this.controller.setView(this);
        topUsers = controller.getTopUsers();
        this.skin = skin;
    }

    @Override
    public void show() {
        table.setFillParent(true);
        table.left();
        table.add(sortByUsername).width(500);
        table.row();
        table.add(sortByScore).width(500);
        table.row();
        table.add(sortByKill).width(500);
        table.row();
        table.add(sortByLivedTime).width(500);
        table.row();
        table.add(backButton).width(200);
        stage.addActor(table);
        showScoreboard();
    }

    public void showScoreboard() {
        scoreboard.clear();
        scoreboard.setFillParent(true);
        scoreboard.center();
        scoreboard.add(usenameLabel).pad(20);
        scoreboard.add(scoreLabel).pad(20);
        scoreboard.add(killLabel).pad(20);
        scoreboard.add(livedTimeLabel).pad(20);
        usenameLabel.setColor(Color.BLUE);
        scoreLabel.setColor(Color.BLUE);
        killLabel.setColor(Color.BLUE);
        livedTimeLabel.setColor(Color.BLUE);
        scoreboard.row();

        for (User user : topUsers) {
            int i = topUsers.indexOf(user);

            Label usernameValue = new Label(user.getUsername(), skin);
            Label scoreValue = new Label(String.valueOf(user.getScore()), skin);
            Label killValue = new Label(String.valueOf(user.getKill()), skin);
            Label livedTimeValue = new Label(String.valueOf(user.getLivedTime()), skin);

            if (i == 0 || i == 1 || i == 2) {
                usernameValue.setColor(Color.RED);
                scoreValue.setColor(Color.RED);
                killValue.setColor(Color.RED);
                livedTimeValue.setColor(Color.RED);
            }

            if (user.equals(App.getCurrentUser())) {
                usernameValue.setColor(Color.GREEN);
                scoreValue.setColor(Color.GREEN);
                killValue.setColor(Color.GREEN);
                livedTimeValue.setColor(Color.GREEN);
            }

            scoreboard.add(usernameValue).pad(20);
            scoreboard.add(scoreValue).pad(20);
            scoreboard.add(killValue).pad(20);
            scoreboard.add(livedTimeValue).pad(20);
            scoreboard.row();
        }
        stage.addActor(scoreboard);
    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleScoreboardMenuButtons();
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

    public TextButton getSortByUsername() {
        return sortByUsername;
    }

    public TextButton getSortByScore() {
        return sortByScore;
    }

    public TextButton getSortByKill() {
        return sortByKill;
    }

    public TextButton getSortByLivedTime() {
        return sortByLivedTime;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
