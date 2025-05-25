package com.tilldown.Controller.MenuControl;

import com.tilldown.Main;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.View.MenuViews.EndGameMenu;
import com.tilldown.View.MenuViews.PauseMenu;

public class PauseMenuController {
    private PauseMenu view;

    public void setView(PauseMenu view) {
        this.view = view;
    }

    public void handlePauseMenuButtons() {
        if (view != null) {
            if (view.getResumeGameButton().isPressed()) {
                Main.getMain().setScreen(view.getGameView());
            } else if (view.getGiveUpButton().isPressed()) {
                view.getGameView().pause();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new EndGameMenu(new EndGameMenuController(), GameAssetManager.getGameAssetManager().getSkin(), false));
            } else if (view.getShowCheatCodesButton().isPressed() && !view.isCheatCodes) {
                view.showCheatCodeGuide();
            } else if (view.getShowAbilitiesButton().isPressed() && !view.isAbility) {
                view.showAbilityGuide();
            }
        }
    }
}
