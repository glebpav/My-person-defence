package ru.mephi.lab.actor.constructions;

import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.abilities.Hittable;
import ru.mephi.lab.actor.abilities.Updatable;

/**
 * 
 */
public class Castle extends BaseActor implements Hittable, Updatable {

    /**
     * Default constructor
     */
    public Castle() {
    }

    /**
     * 
     */
    public double hitPoints;

    /**
     * 
     */
    public double speedOfRegeneration;

    /**
     * 
     */
    public int level;


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