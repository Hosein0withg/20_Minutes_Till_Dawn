package com.tilldown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.MenuControl.HintMenuController;
import com.tilldown.Main;
import com.tilldown.Model.GameAssetManager;
import sun.tools.jconsole.Tab;

public class HintMenu implements Screen {
    private Stage stage;
    private final TextButton showHeroGuide;
    private final TextButton showControllers;
    private final TextButton showCheatCodes;
    private final TextButton showAbilityGuide;
    private final TextButton backButton;
    public Table table;
    public boolean isHeroGuide = false;
    public boolean isControllers = false;
    public boolean isCheatCodes = false;
    public boolean isAbilityGuide = false;
    private final HintMenuController controller;

    public HintMenu(HintMenuController controller, Skin skin) {
        this.controller = controller;
        this.showHeroGuide = new TextButton("Show Hero Guide", skin);
        this.showControllers = new TextButton("Show Controllers", skin);
        this.showCheatCodes = new TextButton("Show Cheat Codes", skin);
        this.showAbilityGuide = new TextButton("Show Ability Guide", skin);
        this.backButton = new TextButton("back", skin);
        this.table = new Table();
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.left().pad(10);
        table.add(showHeroGuide).width(500);
        table.row();
        table.add(showControllers).width(500);
        table.row();
        table.add(showCheatCodes).width(500);
        table.row();
        table.add(showAbilityGuide).width(500);
        table.row();
        table.add(backButton).width(200);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleHintMenuButtons();
    }

    public void showHeroGuide() {
        Label heroGuideLabel = new Label(controller.getHeroGuide(), GameAssetManager.getGameAssetManager().getSkin());
        heroGuideLabel.setPosition(600, 740);
        stage.addActor(heroGuideLabel);
        isHeroGuide = true;
    }

    public void showControllerGuide() {
        Label controllersLabel = new Label(controller.getControllersGuide(), GameAssetManager.getGameAssetManager().getSkin());
        controllersLabel.setPosition(600, 635);
        stage.addActor(controllersLabel);
        isControllers = true;
    }

    public void showCheatCodeGuide() {
        Label cheatCodesLabel = new Label(controller.getCheatCodeGuide(), GameAssetManager.getGameAssetManager().getSkin());
        cheatCodesLabel.setPosition(600, 515);
        stage.addActor(cheatCodesLabel);
        isCheatCodes = true;
    }

    public void showAbilityGuide() {
        Label abilityGuideLabel = new Label(controller.getAbilitiesGuide(), GameAssetManager.getGameAssetManager().getSkin());
        abilityGuideLabel.setPosition(600, 300);
        stage.addActor(abilityGuideLabel);
        isAbilityGuide = true;
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

    public TextButton getShowHeroGuide() {
        return showHeroGuide;
    }

    public TextButton getShowControllers() {
        return showControllers;
    }

    public TextButton getShowCheatCodes() {
        return showCheatCodes;
    }

    public TextButton getShowAbilityGuide() {
        return showAbilityGuide;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
