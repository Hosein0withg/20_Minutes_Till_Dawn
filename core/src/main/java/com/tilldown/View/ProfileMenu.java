package com.tilldown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldown.Controller.GameMenuController;
import com.tilldown.Controller.ProfileMenuController;
import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.Player;

public class ProfileMenu implements Screen {
    private Stage stage;
    private Label information;
    private Image avatarImage;
    private final TextButton changeUsername;
    private final TextButton changePassword;
    private final TextButton changeAvatar;
    private final TextButton deleteAccount;
    private final TextButton backButton;
    public Table table;
    private final ProfileMenuController controller;

    public ProfileMenu(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        Player player = Game.getCurrentPlayer();
        this.changeUsername = new TextButton("Change Username", skin);
        this.changePassword = new TextButton("Change Password", skin);
        this.changeAvatar = new TextButton("Change Avatar", skin);
        this.deleteAccount = new TextButton("Delete Account", skin);
        this.backButton = new TextButton("Back", skin);
        avatarImage = new Image(player.getAvatarDrawable());
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        avatarImage.setSize(150, 150);
        avatarImage.setPosition(200, 700);

        table.setFillParent(true);
        table.center();
        controller.updateInformation();
        information.setColor(Color.YELLOW);
        information.setPosition(200, 900);

        table.add(changeUsername);
        table.row();
        table.add(changePassword);
        table.row();
        table.add(changeAvatar);
        table.row();
        table.add(deleteAccount);
        table.row();
        table.add(backButton);
        stage.addActor(avatarImage);
        stage.addActor(information);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleProfileMenuButtons();
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

    public Label getInformation() {
        return information;
    }

    public void setInformation(Label information) {
        this.information = information;
    }

    public Image getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(Image avatarImage) {
        this.avatarImage = avatarImage;
    }

    public TextButton getChangeUsername() {
        return changeUsername;
    }

    public TextButton getChangePassword() {
        return changePassword;
    }

    public TextButton getChangeAvatar() {
        return changeAvatar;
    }

    public TextButton getDeleteAccount() {
        return deleteAccount;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
