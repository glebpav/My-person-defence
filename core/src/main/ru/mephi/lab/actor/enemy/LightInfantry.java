package ru.mephi.lab.actor.enemy;

import ru.mephi.lab.actor.ActorType;

import static ru.mephi.lab.GameSettings.*;

public class LightInfantry extends Enemy {

    private double probabilityAvoidHit;

    public LightInfantry(float x, float y) {
        super(x, y);
        setWidth(CHARACTER_WIDTH);
        setHeight(CHARACTER_HEIGHT);

        texturePath = TILES_PATH + "enemies/char1.png";

        /*texturePathArray = new ArrayList<>(Arrays.asList(
                TILES_PATH + "enemies/character1_bottom_left.png",
                TILES_PATH + "enemies/character1_bottom_left.png",
                TILES_PATH + "enemies/character1_bottom_left.png",
                TILES_PATH + "enemies/character1_bottom_left.png"
        ));*/

        damageRatio = 0.5f;
        healthPoints = 100;
        actorType = ActorType.ENEMY;
        enemyType = EnemyType.LIGHT_INFANTRY;
    }

    public LightInfantry(float x, float y, float healthPoints) {
        this(x, y);
        this.healthPoints = healthPoints;
    }

    public void regenerateHipPoints() {
        // TODO implement here
    }

}