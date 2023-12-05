package ru.mephi.lab.level;

import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.constructions.Lair;
import ru.mephi.lab.actor.enemy.Enemy;
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.utils.files.JsonProcessor;
import ru.mephi.lab.utils.idHelper.GameIdProcessor;

import java.util.ArrayList;

import static ru.mephi.lab.GameSettings.DEBUG_MODE;

public class GameSession {

    private GameState state;
    public GameField field;
    public GameParams params;
    private int lastGameTick;
    public GameConstructions constructions;

    private final String gameId;
    private final String gamePath;

    private OnFieldChanged onFieldChanged;

    public int pixelHeight;

    public GameSession(String gameId) {
        this.gameId = gameId;
        this.gamePath = GameIdProcessor.getGamePath(gameId);
        if (DEBUG_MODE) System.out.println("Game path is: " + gamePath);
        if (gamePath.isEmpty()) state = GameState.LOADING_ERROR;
    }

    public void setOnFieldChangedListener (OnFieldChanged onFieldChanged) {
        this.onFieldChanged = onFieldChanged;
    }

    public void startGame() {
        loadGame();
        state = GameState.ACTIVE;
    }

    private void loadGame() {

        String rootDir = "assets/systemFiles/gameSaves/" + gamePath + "/";

        field = JsonProcessor.getDeserializedObject(rootDir + "field.json", GameField.class);
        params = JsonProcessor.getDeserializedObject(rootDir + "params.json", GameParams.class);
        constructions = JsonProcessor.getDeserializedObject(rootDir + "constructions.json", GameConstructions.class);

        lastGameTick = params.getCurrentTick();

        if (field == null || params == null || constructions == null) {
            state = GameState.LOADING_ERROR;
            return;
        }

    }

    @SuppressWarnings("NewApi")
    public void makeGameStep() {

        if (lastGameTick != params.getCurrentTick()) {
            ArrayList<Actor> addActors = new ArrayList<>();
            ArrayList<Actor> deletedActors = new ArrayList<>();

            Cell cell;

            for (Lair lair : constructions.lairArray) {
                ArrayList<Enemy> newEnemies = lair.getComingOutEnemies(params.getCurrentTick());

                if (newEnemies != null) {
                    newEnemies.forEach(enemy -> {
                        // lair.addActor(enemy);
                        enemy.setX(lair.getX());
                        enemy.setY(lair.getY());
                        enemy.loadTexture();
                        addActors.add(enemy);

                    });
                }

                lair.removeOutEnemies(params.getCurrentTick());
            }

            for (int x = 0; x < field.fieldHeight; x++) {
                for (int y = 0; y < field.fieldWidth; y++) {
                    cell = field.field.getCell(x, y);
                    for (BaseActor actor : cell.actorsList) {
                        // TODO: IMPLEMENT
                    }
                }
            }

            if (!addActors.isEmpty()) onFieldChanged.onAddActors(addActors);
        }

        lastGameTick = params.getCurrentTick();
        params.nextTick();
    }

    public void getAllActors() {

        onFieldChanged.onAddActors(field.getAllCells());
        onFieldChanged.onAddActors(field.getAllActors());

    }

    public interface OnFieldChanged {
        void onAddActors(ArrayList<Actor> newActors);
    }


}
