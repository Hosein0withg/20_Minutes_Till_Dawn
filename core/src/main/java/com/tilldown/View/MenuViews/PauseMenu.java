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
import com.tilldown.Controller.MenuControl.PauseMenuController;
import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.View.GameView;

public class PauseMenu implements Screen {
    private Stage stage;
    private final TextButton resumeGameButton;
    private final TextButton showCheatCodesButton;
    private final TextButton showAbilitiesButton;
    private final TextButton giveUpButton;
    public Table table;
    private final PauseMenuController controller;
    private final GameView gameView;
    public boolean isCheatCodes = false;
    public boolean isAbility = false;

    public PauseMenu(PauseMenuController controller, Skin skin, GameView gameView) {
        this.controller = controller;
        this.resumeGameButton = new TextButton("Resume Game", skin);
        this.showCheatCodesButton = new TextButton("Cheat Codes", skin);
        this.showAbilitiesButton = new TextButton("Gained Abilities", skin);
        this.giveUpButton = new TextButton("Give Up", skin);
        this.table = new Table();
        this.gameView = gameView;

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(resumeGameButton).width(420);
        table.row();
        table.add(showCheatCodesButton).width(420);
        table.row();
        table.add(showAbilitiesButton).width(420);
        table.row();
        table.add(giveUpButton).width(420);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handlePauseMenuButtons();
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

    public TextButton getResumeGameButton() {
        return resumeGameButton;
    }

    public TextButton getShowCheatCodesButton() {
        return showCheatCodesButton;
    }

    public TextButton getShowAbilitiesButton() {
        return showAbilitiesButton;
    }

    public TextButton getGiveUpButton() {
        return giveUpButton;
    }

    public GameView getGameView() {
        return gameView;
    }

    public void showCheatCodeGuide() {
        Label cheatCodesLabel = new Label("T for decrease game duration - P for increase speed \n                L for level up - H for extra HP",
            GameAssetManager.getGameAssetManager().getSkin());
        cheatCodesLabel.setPosition(50, 565);
        stage.addActor(cheatCodesLabel);
        isCheatCodes = true;
    }

    public void showAbilityGuide() {
        StringBuilder abilities = new StringBuilder();
        for (String gainedAbility : Game.getCurrentUser().gainedAbilities) {
            abilities.append(gainedAbility);
            if (Game.getCurrentUser().gainedAbilities.indexOf(gainedAbility) + 1 != Game.getCurrentUser().gainedAbilities.size()) {
                abilities.append(" - ");
            }

        }
        Label abilityGuideLabel = new Label(abilities, GameAssetManager.getGameAssetManager().getSkin());
        abilityGuideLabel.setPosition(50, 450);
        stage.addActor(abilityGuideLabel);
        isAbility = true;
    }
}
