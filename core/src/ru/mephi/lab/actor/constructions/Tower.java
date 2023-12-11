package ru.mephi.lab.actor.constructions;

import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.abilities.Damageable;
import ru.mephi.lab.actor.abilities.Hittable;
import ru.mephi.lab.actor.abilities.Updatable;

/**
 * 
 */
public class Tower extends BaseActor implements Hittable, Updatable {

    /**
     * Default constructor
     */
    public Tower(float x, float y) {
        super(x, y);
    }

    /**
     * 
     */
    public int damage;

    /**
     * 
     */
    public int level;

    @Override
    public float makeDamage() {
        return 0;
    }

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

    @Override
    public void updateLevel() {

    }
}