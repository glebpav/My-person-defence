package ru.mephi.lab.utils.lair;

import ru.mephi.lab.actor.enemy.Enemy;

import java.util.*;

/**
 * 
 */
public class EnemyArray {

    public int appearanceTime;
    public ArrayList<Enemy> enemies;
    public EnemyArray(int appearanceTime, ArrayList<Enemy> enemies) {
        this.appearanceTime = appearanceTime;
        this.enemies = enemies;
    }

}