package ru.mephi.lab.level;

import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.constructions.Lair;
import ru.mephi.lab.actor.enemy.Enemy;
import ru.mephi.lab.actor.enemy.EnemyType;
import ru.mephi.lab.actor.enemy.LightInfantry;
import ru.mephi.lab.cell.Cell;
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

    WayProcessor wayProcessor;

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
            ArrayList<Actor> addActors = new ArrayList<>();
            ArrayList<Actor> deletedActors = new ArrayList<>();

            Cell cell;
            double sumDamage = 0;

            for (int x = 0; x < field.fieldHeight; x++) {
                for (int y = 0; y < field.fieldWidth; y++) {
                    cell = field.field.getCell(x, y);
                    Position deleteFencePosition = null;
                    for (int i = 0; i < cell.actorsList.size(); i++) {
                        BaseActor actor = cell.actorsList.get(i);
                        if (actor.actorType == ActorType.ENEMY) {
                            ((Enemy) actor).makeStep(wayProcessor);
                            boolean didAttack = false;
                            switch (((Enemy) actor).enemyType) {
                                case LIGHT_INFANTRY, AVIATION -> {
                                    if (actor.shouldAttack((int) wayProcessor.castlePosition.x, (int) wayProcessor.castlePosition.y)) {
                                        sumDamage += actor.makeDamage();
                                        didAttack = true;
                                    }
                                    BaseActor fence = actor.shouldAttackFence(field);
                                    if (fence != null) {
                                        double damageFence = actor.makeDamageFence();
                                        if (fence.getDamage(damageFence)) {
                                            deletedActors.add(fence);
                                            fence.fieldPosition.setPosition(actor.fieldPosition.x, actor.fieldPosition.y);
                                            deleteFencePosition = fence.fieldPosition;
                                        }
                                    }
                                }
                                case HEAVY_INFANTRY -> {

                                }
                            }

                            if (didAttack) {
                                deletedActors.add(cell.actorsList.get(i));
                                cell.actorsList.remove(i);
                                i -= 1;
                            }
                        }
                    }

                    boolean wasFieldModified = deleteFencePosition != null;

                    if (wasFieldModified) {
                        System.out.println("Len before: " + cell.actorsList.size());
                    }

                    if (wasFieldModified) {
                        ArrayList<BaseActor> actors = field.getCeilActor((int) deleteFencePosition.x, (int) deleteFencePosition.y);
                        for (int i = 0; i < actors.size(); i++) {
                            if (actors.get(i).actorType == ActorType.FENCE) {
                                System.out.println("deleting fence");
                                actors.remove(i);
                                i -= 1;
                            }
                        }
                    }

                    if (wasFieldModified) {
                        System.out.println("Len after: " + cell.actorsList.size());
                        wayProcessor.updateConnectionsMatrix(this);
                    }
                }
            }

            if (constructions.castle.getDamage(sumDamage)) {
                state = GameState.LOOSED;
            }

            for (Lair lair : constructions.lairArray) {
                ArrayList<Enemy> newEnemies = lair.getComingOutEnemies(params.getCurrentTick());

                if (newEnemies != null) {
                    newEnemies.forEach(enemy -> {
                        // lair.addActor(enemy);
                        enemy.setX(lair.getX());
                        enemy.setY(lair.getY());
                        enemy.loadTexture();
                        addActors.add(enemy);
                        field.setCeilActor((int) lair.fieldPosition.x, (int) lair.fieldPosition.y, enemy);
                        enemy.fieldPosition.setPosition(lair.fieldPosition.x, lair.fieldPosition.y);
                    });
                }

                lair.removeOutEnemies(params.getCurrentTick());
            }

            if (!deletedActors.isEmpty()) onFieldChanged.onRemoveActors(deletedActors);
            if (!addActors.isEmpty()) onFieldChanged.onAddActors(addActors);
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
