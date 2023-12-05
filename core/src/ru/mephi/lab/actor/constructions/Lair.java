package ru.mephi.lab.actor.constructions;

import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.DrawingType;
import ru.mephi.lab.actor.enemy.Enemy;
import ru.mephi.lab.utils.lair.EnemyArray;

import java.util.*;

import static ru.mephi.lab.GameSettings.*;

/**
 *
 */
public class Lair extends BaseActor {

    public final static int Y_OFFSET = 14;
    public ArrayList<EnemyArray> enemyAppearanceTime;

    public Lair(float x, float y) {
        super(x + (CELL_WIDTH - LAIR_WIDTH) / 2f, y + (0.75f * CELL_HEIGHT - 0.5f * LAIR_HEIGHT + Lair.Y_OFFSET));
        actorType = ActorType.LAIR;
        enemyAppearanceTime = new ArrayList<>();

        setWidth(LAIR_WIDTH);
        setHeight(LAIR_HEIGHT);
        texturePath = TILES_PATH + "lair/lair.png";
        loadTexture();

        drawingType = DrawingType.ONE_TEXTURE;
        actorType = ActorType.LAIR;
    }

    public void addEnemies(EnemyArray enemyArray) {
        enemyAppearanceTime.add(enemyArray);
    }

    public ArrayList<Enemy> getComingOutEnemies(int currentTime) {
        for (EnemyArray enemyArray : enemyAppearanceTime) {
            if (currentTime == enemyArray.appearanceTime) return enemyArray.enemies;
        }
        return null;
    }

    @SuppressWarnings("NewApi")
    public void removeOutEnemies(int time) {
        enemyAppearanceTime.removeIf(enemyArray -> enemyArray.appearanceTime == time);
    }



}