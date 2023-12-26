package ru.mephi.lab.actor.abilities;

import java.util.*;

/**
 * 
 */
public interface Hittable {

    /**
     * @return
     */
    public void regenerateHitPoints();

    /**
     * @param double damage
     * @return
     */
    public boolean getDamage(double damage);

    /**
     * @return
     */
    public boolean isAlive();

}