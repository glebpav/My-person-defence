package ru.mephi.lab.actor.enemy;

import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.abilities.Damageable;
import ru.mephi.lab.actor.abilities.Hittable;

import java.util.*;

/**
 *
 */
public class Enemy extends BaseActor implements Damageable, Hittable {

    public ArrayList<HeroBuff> buffsArray;
    EnemyType enemyType;

    public Enemy(float x, float y) {
        super(x, y);
    }

    public void applyBuff(HeroBuff buff) {
        // TODO implement here
    }

    public void makeStep() {
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