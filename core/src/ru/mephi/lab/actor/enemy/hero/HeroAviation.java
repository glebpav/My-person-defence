package ru.mephi.lab.actor.enemy.hero;

import ru.mephi.lab.actor.enemy.Aviation;
import ru.mephi.lab.actor.enemy.Enemy;
import ru.mephi.lab.actor.enemy.HeroBuff;
import ru.mephi.lab.actor.enemy.hero.Buffing;

import java.util.*;

/**
 * 
 */
public class HeroAviation extends Aviation implements Buffing {

    /**
     * Default constructor
     */
    public HeroAviation() {
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