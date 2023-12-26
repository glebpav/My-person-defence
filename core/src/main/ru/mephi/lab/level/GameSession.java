package ru.mephi.lab.level;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sun.jdi.event.ThreadStartEvent;
import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.constructions.Lair;
import ru.mephi.lab.actor.constructions.Tower;
import ru.mephi.lab.actor.enemy.Enemy;
import ru.mephi.lab.actor.enemy.EnemyType;
import ru.mephi.lab.actor.enemy.LightInfantry;
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.threads.TreadStepExecutor;
import ru.mephi.lab.utils.files.JsonProcessor;
import ru.mephi.lab.utils.geometry.GeometryHelper;
import ru.mephi.lab.utils.idHelper.GameIdProcessor;
import ru.mephi.lab.utils.way.WayProcessor;

import java.util.ArrayList;
import java.util.Arrays;

import static ru.mephi.lab.GameSettings.*;

public class GameSession {

    public GameState state;
    public GameField field;
    public GameParams params;
    private int lastGameTick;
    public GameConstructions constructions;

    public WayProcessor wayProcessor;

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

    public void setOnFieldChangedListener(OnFieldChanged onFieldChanged) {
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
        wayProcessor = new WayProcessor(field.fieldHeight, field.fieldWidth, this);

        if (field == null || params == null || constructions == null) {
            state = GameState.LOADING_ERROR;
            return;
        }

    }

    @SuppressWarnings("NewApi")
    public void makeGameStep() {

        if (lastGameTick != params.getCurrentTick()) {

            TreadStepExecutor treadStepExecutor = new TreadStepExecutor(this);
            treadStepExecutor.start(4);

            ArrayList<Actor> deletedActors = treadStepExecutor.removedActors;
            ArrayList<Actor> addActors = new ArrayList<>();

            int sumDamage = (int) treadStepExecutor.sumCastleDamage;
            boolean hasAnyEnemyFound = treadStepExecutor.hasAnyEnemyFound;

            if (constructions.castle.getDamage(sumDamage)) {
                state = GameState.LOOSED;
            }

            for (Lair lair : constructions.lairArray) {
                ArrayList<Enemy> newEnemies = lair.getComingOutEnemies(params.getCurrentTick());
                hasAnyEnemyFound = hasAnyEnemyFound || !lair.enemyAppearanceTime.isEmpty();

                if (newEnemies != null) {
                    newEnemies.forEach(enemy -> {
                        enemy.setX(lair.getX());
                        enemy.setY(lair.getY());
                        enemy.loadTexture();
                        addActors.add(enemy);
                        field.setCeilActor((int) lair.fieldPosition.x, (int) lair.fieldPosition.y, enemy);
                        enemy.fieldPosition.setPosition(lair.fieldPosition.y, lair.fieldPosition.x);
                    });
                }

                lair.removeOutEnemies(params.getCurrentTick());
            }

            for (Tower tower : constructions.towersArray) {
                for (Enemy enemy : treadStepExecutor.availableEnemies) {
                    tower.inInAttackRadius((int) enemy.fieldPosition.x, (int) enemy.fieldPosition.y);
                    float damage = tower.makeDamage();
                    // enemy.get
                }
            }

            if (!deletedActors.isEmpty()) onFieldChanged.onRemoveActors(deletedActors);
            if (!addActors.isEmpty()) onFieldChanged.onAddActors(addActors);

            if (!hasAnyEnemyFound) {
                state = GameState.ENDED;
            }
        }

        lastGameTick = params.getCurrentTick();
        params.nextTick();
    }

    public void getAllActors() {

        onFieldChanged.onAddActors(field.getAllCells());
        onFieldChanged.onAddBaseActors(field.getAllActors());

    }

    public interface OnFieldChanged {
        void onAddActors(ArrayList<Actor> newActors);

        void onAddBaseActors(ArrayList<BaseActor> newActors);

        void onRemoveActors(ArrayList<Actor> removedActors);
    }


}