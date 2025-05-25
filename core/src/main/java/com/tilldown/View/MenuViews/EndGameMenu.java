package com.tilldown.View.MenuViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.MenuControl.EndGameMenuController;
import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.Model.Player;
import com.tilldown.Model.User;

public class EndGameMenu implements Screen {
    private Stage stage;
    public Table table;
    private final TextButton goToGameMenu;
    private final Label isWinLabel;
    private final Label usernameLabel;
    private final Label aliveTimeLabel;
    private final Label scoreLabel;
    private final Label killsLabel;

    private final EndGameMenuController controller;

    public EndGameMenu(EndGameMenuController controller, Skin skin, boolean won) {
        User user = Game.getCurrentUser();
        Player player = user.getCurrentHero();
        int score = Game.gameTimePassed * player.getKill();
        user.setScore(user.getScore() + score);
        user.setKill(user.getKill() + player.getKill());
        if (Game.gameTimePassed > user.getLivedTime())
            user.setLivedTime(Game.gameTimePassed);

        this.controller = controller;
        this.goToGameMenu = new TextButton("Go To Game Menu", skin);
        if (won) {
            this.isWinLabel = new Label("! ! WON ! !", skin);
            if (Game.getCurrentUser().isSfx())
                GameAssetManager.getGameAssetManager().getWinSound().play(1.0f);
        } else {
            this.isWinLabel = new Label("! ! ! LOST ! !", skin);
            if (Game.getCurrentUser().isSfx())
                GameAssetManager.getGameAssetManager().getLooseSound().play(1.0f);
        }
        this.usernameLabel = new Label("Username: " + Game.getCurrentUser().getUsername(), skin);
        this.scoreLabel = new Label("Score: " + score, skin);
        this.killsLabel = new Label("Kills: " + Game.getCurrentUser().getKill(), skin);
        this.aliveTimeLabel = new Label("Lived for " + Game.gameTimePassed + " seconds", skin);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
        table.center();
        table.add(isWinLabel);
        table.row();
        table.add(usernameLabel);
        table.row();
        table.add(scoreLabel);
        table.row();
        table.add(killsLabel);
        table.row();
        table.add(aliveTimeLabel);
        table.row();
        table.add(goToGameMenu);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleEndGameMenuButtons();
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

    public TextButton getGoToGameMenu() {
        return goToGameMenu;
    }
}
