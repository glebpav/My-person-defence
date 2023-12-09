package ru.mephi.lab.actor.enemy;

import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.abilities.Damageable;
import ru.mephi.lab.actor.abilities.Hittable;
import ru.mephi.lab.utils.geometry.GeometryHelper;
import ru.mephi.lab.utils.way.WayProcessor;

import java.util.*;

/**
 *
 */
public class Enemy extends BaseActor implements Damageable, Hittable {

    public ArrayList<HeroBuff> buffsArray;
    EnemyType enemyType;

    public Enemy(float x, float y) {
        super(x, y);
        actorType = ActorType.ENEMY;
    }

    public void applyBuff(HeroBuff buff) {
        // TODO implement here
    }

    public void makeStep(WayProcessor wayProcessor) {
        Position postition = GeometryHelper.reverseCoords(getX(), getY());
        wayProcessor.getNextPosition((int) postition.x, (int) postition.y, enemyType);
    }

    @Override
    public float makeDamage() {
        return 0;
    }

    @Override
    public void regenerateHitPoints() {

    }

    @Override
    public void getDamage(double damage) {

    }

    @Override
    public boolean isAlive() {
        return false;
    }
}