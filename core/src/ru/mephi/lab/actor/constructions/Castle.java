package ru.mephi.lab.actor.constructions;

import ru.mephi.lab.actor.Actor;
import ru.mephi.lab.actor.abilities.Hittable;
import ru.mephi.lab.actor.abilities.Updatable;

import java.util.*;

/**
 * 
 */
public class Castle extends Actor implements Hittable, Updatable {

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