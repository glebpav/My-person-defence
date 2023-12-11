package ru.mephi.lab.level;

import com.google.gson.annotations.SerializedName;

import static ru.mephi.lab.GameSettings.TICK_DURATION;

public class GameParams {



    @SerializedName("currentTick")
    private int currentTick;
    @SerializedName("currentBank")
    private int currentBank;

    public int getCurrentTick() {
        return currentTick / TICK_DURATION;
    }

    public int getCurrentBank() {
        return currentBank;
    }

    public void nextTick() {
        currentTick += 1;
    }

    public void addMoney(int money) {
        currentBank += money;
    }


}
