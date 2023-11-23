package ru.mephi.lab.level;

import ru.mephi.lab.utils.idHelper.GameIdProcessor;

import static ru.mephi.lab.GameSettings.DEBUG_MODE;

public class GameSession {

    private GameState state;
    private GameParams params;

    private final String gameId;
    private final String gamePath;

    public GameSession(String gameId) {
        this.gameId = gameId;
        this.gamePath = GameIdProcessor.getGamePath(gameId);
        if (DEBUG_MODE) System.out.println("Game path is: " + gamePath);
        if (gamePath.isEmpty()) state = GameState.LOADING_ERROR;
    }

    public void startGame() {
        loadGame();
        state = GameState.ACTIVE;
    }

    private void loadGame() {
        // load params
        // load field
    }


}
