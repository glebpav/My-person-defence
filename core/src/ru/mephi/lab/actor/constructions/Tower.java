package ru.mephi.lab.actor.constructions;

import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.DrawingType;
import ru.mephi.lab.actor.abilities.Damageable;
import ru.mephi.lab.actor.abilities.Hittable;
import ru.mephi.lab.actor.abilities.Updatable;

import java.util.ArrayList;
import java.util.logging.Level;

import static ru.mephi.lab.GameSettings.*;

/**
 * 
 */
public class Tower extends BaseActor implements Updatable{

    public float damage;
    public float attackRadius;
    public int level;
    public double speedOfRegeneration;

    public Tower(float x, float y) {
        super(x + (CELL_WIDTH - CONSTRUCTION_WIDTH) / 2f, y + (0.75f * CELL_HEIGHT - 0.5f * CONSTRUCTION_HEIGHT + Lair.Y_OFFSET));

        setWidth(CONSTRUCTION_WIDTH);
        setHeight(CONSTRUCTION_HEIGHT);

        drawingType = DrawingType.ONE_TEXTURE;
        actorType = ActorType.TOWER;

        texturePath = TILES_PATH + "tower/" + TowerLevelDescriptor.getLevelDescriptor(level).texturePath;
        System.out.println(texturePath);
        loadTexture();

        damage = (int) TowerLevelDescriptor.getLevelDescriptor(level).damage;
        attackRadius = (int) TowerLevelDescriptor.getLevelDescriptor(level).attackRadius;
    }

    @Override
    public float makeDamage() {
        return damage;
    }

    public boolean inInAttackRadius(int enemyX, int enemyY) {
        float dx = enemyX - fieldPosition.x;
        float dy = enemyY - fieldPosition.y;
        return dx * dx + dy * dy <= attackRadius * attackRadius;
    }

    @Override
    public void regenerateHitPoints() {

    }

    @Override
    public void updateLevel() {

    }

    @Override
    public boolean hasNextLevel() {
        return false;
    }
}