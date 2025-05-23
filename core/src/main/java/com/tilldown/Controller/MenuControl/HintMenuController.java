package com.tilldown.Controller.MenuControl;

import com.tilldown.Main;
import com.tilldown.Model.Game;
import com.tilldown.Model.GameAssetManager;
import com.tilldown.View.*;

public class HintMenuController {
    private HintMenu view;

    public void setView(HintMenu view) {
        this.view = view;
    }

    public void handleHintMenuButtons() {
        if (view != null) {
            if (view.getBackButton().isPressed()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameMenu(new GameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getShowHeroGuide().isPressed() && !view.isHeroGuide) {
                this.view.showHeroGuide();
            } else if (view.getShowControllers().isPressed() && !view.isControllers) {
                this.view.showControllerGuide();
            } else if (view.getShowCheatCodes().isPressed() && !view.isCheatCodes) {
                this.view.showCheatCodeGuide();
            } else if (view.getShowAbilityGuide().isPressed() && !view.isAbilityGuide) {
                this.view.showAbilityGuide();
            }
        }
    }

    public String getHeroGuide() {
        return "top heroes by speed -> Dasher: 4  |  Scarlet: 5  |  Shana: 4";
    }

    public String getControllersGuide() {
        if (Game.getCurrentUser().isNumberController()) {
            return "active controller is numpad: 8-4-2-6 for up-left-down-right";
        } else {
            return "active controller is wasd: w-a-s-d for up-left-down-right";
        }
    }

    public String getCheatCodeGuide() {
        return "T for decrease game duration - P for increase speed - L for level up - H for extra HP";
    }

    public String getAbilitiesGuide() {
        return "Vitality -> increase max HP (1 unit)\n" +
            "Damager -> increase gun damage for 10 sec(25%)\n" +
            "Procrease -> increase projectile of gun (1 unit)\n" +
            "Amocrease -> increase max amo of gun (5 unit)\n" +
            "Speedy -> double hero speed for 10 sec";
    }
}
