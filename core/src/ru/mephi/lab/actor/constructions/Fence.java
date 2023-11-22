package ru.mephi.lab.actor.constructions;

import ru.mephi.lab.actor.Actor;
import ru.mephi.lab.actor.abilities.Hittable;

import java.util.*;

/**
 * 
 */
public class Fence extends Actor implements Hittable {

    /**
     * Default constructor
     */
    public Fence() {
    }

    /**
     * 
     */
    public double hitPoints;

    /**
     * 
     */
    public double maxHitPoints;

    /**
     * 
     */
    public double speedOfRegeneration;


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