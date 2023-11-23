package ru.mephi.lab.level;

import ru.mephi.lab.utils.idHelper.GameIdProcessor;

public class GameSession {

    public GameState state;
    public int countOfMoney;
    private final String gameId;
    private final String gamePath;

    public GameSession(String gameId) {
        this.gameId = gameId;
        this.gamePath = GameIdProcessor.getGamePath(gameId);
        System.out.println("Game path is: " + gamePath);
    }

    public void startGame() {
        state = GameState.ACTIVE;
        countOfMoney = 0;
    }

    private void load() {

    }


}
