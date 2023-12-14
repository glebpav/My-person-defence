package ru.mephi.lab.actor.enemy;

import ru.mephi.lab.GameSettings;
import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.abilities.Hittable;
import ru.mephi.lab.actor.constructions.Fence;
import ru.mephi.lab.level.GameField;
import ru.mephi.lab.utils.geometry.GeometryHelper;
import ru.mephi.lab.utils.way.WayProcessor;

import java.util.*;

public class Enemy extends BaseActor implements Hittable {

    public ArrayList<HeroBuff> buffsArray;
    public EnemyType enemyType;

    protected int leftAmmunitionCount;
    protected int fenceAttackRadius;
    float damageRatio;

    ArrayList<Position> nextSteps;

    public Enemy(float x, float y) {
        super(x, y);
        actorType = ActorType.ENEMY;
        nextSteps = new ArrayList<>();
    }

    public void applyBuff(HeroBuff buff) {
        // TODO implement here
    }

    public void makeStep(WayProcessor wayProcessor) {

        nextSteps = wayProcessor.getNextPosition((int) fieldPosition.x, (int) fieldPosition.y, enemyType);

        if (nextSteps != null) {
            fieldPosition.setPosition(nextSteps.get(0).x, nextSteps.get(0).y);
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
            case LIGHT_INFANTRY, AVIATION, HEAVY_INFANTRY -> castleX == fieldPosition.x && castleY == fieldPosition.y;
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
            case HEAVY_INFANTRY -> {

                int x = (int) fieldPosition.x;
                int y = (int) fieldPosition.y;

                if (nextSteps == null) yield null;

                // System.out.println(nextSteps);

                for (Position possiblePosition : nextSteps) {
                    float possibleX = x - possiblePosition.x;
                    float possibleY = y - possiblePosition.y;

                    ArrayList<BaseActor> actors = gameField.getCeilActor((int) possiblePosition.x, (int) possiblePosition.y);
                    BaseActor fence = null;
                    for (int i = 0; i < actors.size(); i++) {
                        if (actors.get(i).actorType == ActorType.FENCE) {
                            fence = actors.get(i);
                            fence.fieldPosition.setPosition(x, y);
                            break;
                        }
                    }

                    if (fence != null && possibleX * possibleX + possibleY * possibleY <= fenceAttackRadius * fenceAttackRadius) {
                        yield fence;
                    }
                }

                yield null;

            }
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
            return GameSettings.AVIATION_FENCE_DAMAGE;
        }

        if (enemyType == EnemyType.HEAVY_INFANTRY) {
            return GameSettings.HEAVY_INFANTRY_FENCE_DAMAGE;
        }

        return 0;
    }
}