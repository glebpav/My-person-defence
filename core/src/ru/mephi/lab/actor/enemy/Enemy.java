package ru.mephi.lab.actor.enemy;

import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.abilities.Hittable;
import ru.mephi.lab.level.GameField;
import ru.mephi.lab.utils.geometry.GeometryHelper;
import ru.mephi.lab.utils.way.WayProcessor;

import java.util.*;

/**
 *
 */
public class Enemy extends BaseActor implements Hittable {

    public ArrayList<HeroBuff> buffsArray;
    public EnemyType enemyType;

    protected int leftAmmunitionCount;

    float damageRatio;

    public Enemy(float x, float y) {
        super(x, y);
        actorType = ActorType.ENEMY;
    }

    public void applyBuff(HeroBuff buff) {
        // TODO implement here
    }

    public void makeStep(WayProcessor wayProcessor) {

        Position nextPosition = wayProcessor.getNextPosition((int) fieldPosition.x, (int) fieldPosition.y, enemyType);

        if (nextPosition != null) {
            fieldPosition.setPosition(nextPosition.x, nextPosition.y);
        }

        Position position = GeometryHelper.convertCoordsToCellCenter(fieldPosition.x, fieldPosition.y);

        setX(position.x);
        setY(position.y);
    }

    @Override
    public float makeDamage() {
        return (float) (healthPoints * damageRatio);
    }

    @Override
    public boolean shouldAttack(int castleX, int castleY) {
        return switch (enemyType) {
            case HEAVY_INFANTRY -> false;
            case LIGHT_INFANTRY, AVIATION -> castleX == fieldPosition.x && castleY == fieldPosition.y;
        };
    }

    @Override
    public void regenerateHitPoints() {

    }

    @Override
    public boolean getDamage(double damage) {
        return false;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public BaseActor shouldAttackFence(GameField gameField) {
        return switch (enemyType) {
            case HEAVY_INFANTRY -> null;
            case LIGHT_INFANTRY -> null;
            case AVIATION -> {
                if (leftAmmunitionCount <= 0) yield null;
                ArrayList<BaseActor> actorList = gameField.getCeilActor((int) fieldPosition.x, (int) fieldPosition.y);

                if (actorList.isEmpty()) yield null;

                for (BaseActor actor : actorList) {
                    if (actor.actorType == ActorType.FENCE) {
                        yield actor;
                    }
                }

                yield null;
            }
        };
    }

    @Override
    public float makeDamageFence() {
        if (enemyType == EnemyType.AVIATION && leftAmmunitionCount > 0){
            leftAmmunitionCount -= 1;
            return makeDamage();
        }


        return 0;
    }
}