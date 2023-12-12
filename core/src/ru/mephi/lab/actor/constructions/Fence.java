package ru.mephi.lab.actor.constructions;

import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.DrawingType;
import ru.mephi.lab.actor.abilities.Hittable;

import static ru.mephi.lab.GameSettings.*;

public class Fence extends BaseActor implements Hittable {


    public Fence(float x, float y) {
        super(x + (CELL_WIDTH - CONSTRUCTION_WIDTH) / 2f, y + (0.75f * CELL_HEIGHT - 0.5f * CONSTRUCTION_HEIGHT + Lair.Y_OFFSET));

        setWidth(CONSTRUCTION_WIDTH);
        setHeight(CONSTRUCTION_HEIGHT);
        texturePath = TILES_PATH + "fence/fence.png";
        loadTexture();

        healthPoints = FENCE_DEFAULT_HEALTH_POINTS;
        drawingType = DrawingType.ONE_TEXTURE;
        actorType = ActorType.FENCE;

    }

    public double hitPoints;

    public double maxHitPoints;

    public double speedOfRegeneration;


    @Override
    public void regenerateHitPoints() {

    }

    @Override
    public boolean getDamage(double damage) {
        return false;
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}