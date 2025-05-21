package com.tilldown.Controller.MenuControl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.tilldown.Main;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.View.GameMenu;
import com.tilldown.View.MusicMenu;

public class MusicMenuController {
    private static MusicMenuController instance;
    private Music currentMusic;
    private MusicMenu view;

    public static MusicMenuController getInstance() {
        if (instance == null) {
            instance = new MusicMenuController();
        }
        return instance;
    }

    public void setView(MusicMenu view) {
        this.view = view;
    }

    public void playMusic(String filePath) {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
        }

        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(filePath));
        currentMusic.setLooping(true);
        currentMusic.play();
    }

    public void setVolume(float volume) {
        if (currentMusic != null) {
            currentMusic.setVolume(volume);
        }
    }

    public void stopMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
        }
    }

    public void handleMusicButtons() {
        if (view != null) {
            if (view.getBackButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameMenu(new GameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
