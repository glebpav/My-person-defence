package ru.mephi.lab.level;

import com.google.gson.annotations.SerializedName;

public class GameParams {

    public static final int TICK_DURATION = 60;

    @SerializedName("currentTick")
    private int currentTick;
    @SerializedName("currentBank")
    private int currentBank;

    public int getCurrentTick() {
        // System.out.println(currentTick / TICK_DURATION);
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
