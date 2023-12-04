package ru.mephi.lab.actor.enemy;

import ru.mephi.lab.actor.enemy.Enemy;

import java.util.*;

import static ru.mephi.lab.GameSettings.*;

public class LightInfantry extends Enemy {

    private double probabiltyAvoidHit;

    public LightInfantry(float x, float y) {
        super(x, y);
        setWidth(LIGHT_INFANTRY_WIDTH);
        setHeight(LIGHT_INFANTRY_HEIGHT);

        texturePath = TILES_PATH + "enemies/char1.png";

        /*texturePathArray = new ArrayList<>(Arrays.asList(
                TILES_PATH + "enemies/character1_bottom_left.png",
                TILES_PATH + "enemies/character1_bottom_left.png",
                TILES_PATH + "enemies/character1_bottom_left.png",
                TILES_PATH + "enemies/character1_bottom_left.png"
        ));*/
    }

    @Override
    public float makeDamage() {
        // TODO implement here
        return 0;
    }

    @Override
    public void makeStep() {

    }

    /**
     * @return
     */
    public void regenerateHipPoints() {
        // TODO implement here
    }

}