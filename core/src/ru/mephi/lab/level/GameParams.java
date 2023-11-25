package ru.mephi.lab.level;

import com.google.gson.annotations.SerializedName;

public class GameParams {

    @SerializedName("currentTick")
    private int currentTick;
    @SerializedName("currentBank")
    private int currentBank;

    public int getCurrentTick() {
        return currentTick;
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
