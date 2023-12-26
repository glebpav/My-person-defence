package ru.mephi.lab.actor.enemy;

import ru.mephi.lab.actor.ActorType;

import static ru.mephi.lab.GameSettings.*;

/**
 * 
 */
public class Aviation extends Enemy {

    public Aviation(float x, float y) {
        super(x, y);
        setWidth(CHARACTER_WIDTH);
        setHeight(CHARACTER_HEIGHT);

        texturePath = TILES_PATH + "enemies/plane.png";

        leftAmmunitionCount = 2;
        damageRatio = 0.3f;
        healthPoints = 30;
        actorType = ActorType.ENEMY;
        enemyType = EnemyType.AVIATION;
    }

    public Aviation(float x, float y, float healthPoints, int leftAmmunitionCount) {
        this(x, y);
        this.healthPoints = healthPoints;
        this.leftAmmunitionCount = leftAmmunitionCount;
    }


    public float makeDamage() {
        // TODO implement here
        return 0;
    }

    public void makeStep() {
        // TODO implement here
    }

    public void regenerateHipPoints() {
        // TODO implement here
    }

}