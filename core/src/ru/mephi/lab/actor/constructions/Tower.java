package ru.mephi.lab.actor.constructions;

import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.abilities.Damageable;
import ru.mephi.lab.actor.abilities.Hittable;
import ru.mephi.lab.actor.abilities.Updatable;

/**
 * 
 */
public class Tower extends BaseActor implements Hittable, Damageable, Updatable {

    /**
     * Default constructor
     */
    public Tower() {
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
    public void getDamage(double damage) {

    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void updateLevel() {

    }
}