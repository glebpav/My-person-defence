package ru.mephi.lab.actor.enemy;

import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.enemy.Enemy;
import ru.mephi.lab.utils.way.WayProcessor;

import java.util.*;

import static ru.mephi.lab.GameSettings.*;

public class HeavyInfantry extends Enemy {

    public HeavyInfantry(float x, float y) {
        super(x, y);
        setWidth(CHARACTER_WIDTH);
        setHeight(CHARACTER_HEIGHT);

        texturePath = TILES_PATH + "enemies/heavyInfantry.png";

        damageRatio = 0.5f;
        healthPoints = 80;
        fenceAttackRadius = 2;
        actorType = ActorType.ENEMY;
        enemyType = EnemyType.HEAVY_INFANTRY;
    }

    public HeavyInfantry(float x, float y, float healthPoints, int fenceAttackRadius) {
        this(x, y);
        this.healthPoints = healthPoints;
        this.fenceAttackRadius = fenceAttackRadius;
    }

    public float makeDamage() {
        // TODO implement here
        return 0;
    }

    @Override
    public void makeStep(WayProcessor wayProcessor) {
        // TODO implement here
    }

    public void regenerateHipPoints() {
        // TODO implement here
    }

}