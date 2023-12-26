package ru.mephi.lab.actor.enemy.hero;

import ru.mephi.lab.actor.enemy.Enemy;
import ru.mephi.lab.actor.enemy.HeroBuff;
import ru.mephi.lab.actor.enemy.LightInfantry;
import ru.mephi.lab.actor.enemy.hero.Buffing;

import java.util.*;

/**
 * 
 */
public class HeroLightInfantry extends LightInfantry implements Buffing {

    /**
     * Default constructor
     */
    public HeroLightInfantry(float x, float y) {
        super(x, y);
    }

    /**
     * 
     */
    public int radiusBuff;

    /**
     * 
     */
    public HeroBuff appliedBuff;

    @Override
    public void buffEnemies(ArrayList<Enemy> enemyArray) {

    }
}