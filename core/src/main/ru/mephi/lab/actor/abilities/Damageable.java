package ru.mephi.lab.actor.abilities;

import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.level.GameField;

import java.util.*;

/**
 * 
 */
public interface Damageable {

    public float makeDamage();

    public float makeDamageFence();

    public boolean shouldAttack(int castleX, int castleY);

    public BaseActor shouldAttackFence(GameField gameField);

}