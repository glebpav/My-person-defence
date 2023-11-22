package ru.mephi.lab.actor.enemy.hero;

import ru.mephi.lab.actor.enemy.Enemy;
import ru.mephi.lab.actor.enemy.HeavyInfantry;
import ru.mephi.lab.actor.enemy.HeroBuff;
import ru.mephi.lab.actor.enemy.hero.Buffing;

import java.util.*;

/**
 * 
 */
public class HeroHeavyInfantry extends HeavyInfantry implements Buffing {

    /**
     * Default constructor
     */
    public HeroHeavyInfantry() {
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