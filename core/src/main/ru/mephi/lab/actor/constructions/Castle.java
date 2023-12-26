package ru.mephi.lab.actor.constructions;

import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.DrawingType;
import ru.mephi.lab.actor.abilities.Hittable;
import ru.mephi.lab.actor.abilities.Updatable;

import java.util.ArrayList;

import static ru.mephi.lab.GameSettings.*;


public class Castle extends BaseActor implements Hittable, Updatable {

    public static int Y_OFFSET = 10;

    public int level;
    public double speedOfRegeneration;

    public Castle(int level, float x, float y) {
        super(x + (CELL_WIDTH - CASTLE_WIDTH) / 2f, y + (0.75f * CELL_HEIGHT - 0.5f * CASTLE_HEIGHT + Castle.Y_OFFSET));

        setWidth(CASTLE_WIDTH);
        setHeight(CASTLE_HEIGHT);

        texturePathArray = new ArrayList<>();
        texturePathArray.add(TILES_PATH + "castle/castle_level_1.png");
        texturePathArray.add(TILES_PATH + "castle/castle_level_2.png");
        texturePathArray.add(TILES_PATH + "castle/castle_level_3.png");
        selectedFrameIdx = 0;

        drawingType = DrawingType.UPDATABLE_TEXTURE;
        selectedFrameIdx = level;

        healthPoints = 150;
        actorType = ActorType.CASTLE;
    }

    public Castle(int level, float x, float y, double healthPoints) {
        this(level, x, y);
        this.healthPoints = healthPoints;
    }

    @Override
    public void regenerateHitPoints() {

    }

    @Override
    public boolean getDamage(double damage) {
        healthPoints -= damage;
        // System.out.println("left healthPoints: " + healthPoints);
        return healthPoints <= 0;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void updateLevel() {

    }

    @Override
    public boolean hasNextLevel() {
        return false;
    }
}