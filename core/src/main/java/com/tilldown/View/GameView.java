package com.tilldown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.GameController;
import com.tilldown.Main;
import com.tilldown.Model.*;


public class GameView implements Screen, InputProcessor {
    private Stage stage;
    private Texture cursorTexture;
    private Sprite cursorSprite;
    private GameController controller;
    Table table = new Table();
    Label timerLabel;
    Label HPLabel;
    Label killLabel;
    Label ammoLeftLabel;
    Label levelLabel;
    ProgressBar xpBar;
    Label abilityLabel;
    public boolean showAbility = false;
    public String ability = "";
    Skin skin;

    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        Gdx.input.setCursorCatched(true);
        Gdx.input.setCursorPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        cursorTexture = new Texture("cursor.png");
        cursorSprite = new Sprite(cursorTexture);
        this.skin = skin;
    }

    @Override
    public void show() {
        table.clear();
        Player hero = Game.getCurrentUser().getCurrentHero();
        timerLabel = new Label("Time Left: " + formatTime(controller.getGameTime()), skin);
        HPLabel = new Label("HP: " + (int) hero.getHP(), skin);
        killLabel = new Label("Kill: " + hero.getKill(), skin);
        ammoLeftLabel = new Label("Ammo Left: " + hero.getAmmoLeft(), skin);
        levelLabel = new Label("Level: " + hero.getLevel(), skin);
        abilityLabel = new Label("Ability: ", skin);
        xpBar = new ProgressBar(0, Game.getCurrentUser().getCurrentHero().calculateNextLevelXP(), 1, false, GameAssetManager.getGameAssetManager().getSkin());
        xpBar.setValue(Game.getCurrentUser().getCurrentHero().getXP());
        stage = new Stage(new ScreenViewport());
        table.top().left().setFillParent(true);
        Gdx.input.setInputProcessor(this);
        table.add(timerLabel).pad(30);
        table.add(HPLabel).pad(20);
        table.add(ammoLeftLabel).pad(20);
        table.add(levelLabel).pad(20);
        table.add(xpBar).pad(20);
        table.add(killLabel).pad(20);
        table.add(abilityLabel).pad(20);
        stage.addActor(table);

        if (cursorTexture == null || cursorTexture.getTextureObjectHandle() == 0) {
            cursorTexture = new Texture("cursor.png");
            cursorSprite = new Sprite(cursorTexture);
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        timerLabel.setText("Time Left: " + formatTime(controller.getGameTime()));
        ammoLeftLabel.setText("Ammo Left: " + Game.getCurrentUser().getCurrentHero().getAmmoLeft());
        levelLabel.setText("Level: " + Game.getCurrentUser().getCurrentHero().getLevel());
        if (showAbility) {
            abilityLabel.setText("Ability: " + ability);
        } else {
            abilityLabel.setText("Ability: nothing");
        }
        HPLabel.setText("HP: " + (int) Game.getCurrentUser().getCurrentHero().getHP());
        killLabel.setText("Kill: " + Game.getCurrentUser().getCurrentHero().getKill());
        xpBar.setRange(0, Game.getCurrentUser().getCurrentHero().calculateNextLevelXP());
        xpBar.setValue(Game.getCurrentUser().getCurrentHero().getXP());

        cursorSprite.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

        Main.getBatch().begin();
        controller.updateGame(delta);

        for (Tree tree : Game.trees) {
            Main.getBatch().draw(
                tree.getSprite().getTexture(),
                tree.getWorldX(),
                tree.getWorldY(),
                tree.getSprite().getWidth(),
                tree.getSprite().getHeight()
            );
        }

        for (Seed seed : Game.seedsOnMap) {
            Main.getBatch().draw(seed.getTexture(), seed.getPosX(), seed.getPosY());
        }

        cursorSprite.draw(Main.getBatch());

        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        Gdx.input.setCursorCatched(false);
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        cursorTexture.dispose();
        stage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controller.getWeaponController().handleWeaponShoot(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    private String formatTime(float time) {
        int minutes = (int) (time / 60);
        int seconds = (int) (time % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }
}
