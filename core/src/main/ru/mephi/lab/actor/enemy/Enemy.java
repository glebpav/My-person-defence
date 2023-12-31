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

/**
 * Class that declare special type of actor that can make damage to constructions
 */
public class Enemy extends BaseActor implements Hittable {

    /**
     * Array of buffs that applied at this moment
     */
    public ArrayList<HeroBuff> buffsArray;

    /**
     * field that shows type of this enemy
     */
    public EnemyType enemyType;

    /**
     * field that shows count of left ammunition -> count of available attack on fence
     */
    protected int leftAmmunitionCount;
    /**
     *  field that shows in witch radius enemy can attack fence
     */
    protected int fenceAttackRadius;
    /**
     * Field that shows on what ratio damage can be multiplied
     */
    float damageRatio;

    /**
     * Array of future steps
     */
    ArrayList<Position> nextSteps;

    /**
     * Base enemy constructor
     * @param x - x position on the screen
     * @param y - y position on the screen
     */
    public Enemy(float x, float y) {
        super(x, y);
        actorType = ActorType.ENEMY;
        nextSteps = new ArrayList<>();
    }

    /**
     * Base method that applies buf from hero
     * @param buff - applicable buff
     */
    public void applyBuff(HeroBuff buff) {

    }

    /**
     * Method that move actor to another position by on step
     * @param wayProcessor - object of helper class for finding shortest way
     */
    public void makeStep(WayProcessor wayProcessor) {

        nextSteps = wayProcessor.getNextPosition((int) fieldPosition.x, (int) fieldPosition.y, enemyType);

        if (nextSteps != null) {
            fieldPosition.setPosition(nextSteps.get(0).x, nextSteps.get(0).y);
        }

        Position position = GeometryHelper.convertCoordsToCellCenter(fieldPosition.x, fieldPosition.y);

        setX(position.x);
        setY(position.y);
    }

    /**
     * Method that return damage that this actor can make to another alive actor
     *
     * @return base damage
     */
    @Override
    public float makeDamage() {
        return (float) (healthPoints * damageRatio);
    }

    /**
     * Method witch show if actor should attack castle on this step
     *
     * @param castleX - x position of castle
     * @param castleY - y position of castle
     * @return shouldAttack
     */
    @Override
    public boolean shouldAttack(int castleX, int castleY) {
        return switch (enemyType) {
            case LIGHT_INFANTRY, AVIATION, HEAVY_INFANTRY -> castleX == fieldPosition.x && castleY == fieldPosition.y;
        };
    }

    /**
     * Base method that increase health points of actor
     */
    @Override
    public void regenerateHitPoints() {

    }

    /**
     * Base method witch is invoking when actor gets damage
     *
     * @param damage - count of damage witch actor gets
     * @return is actor is still alive
     */
    @Override
    public boolean getDamage(double damage) {
        return false;
    }

    /**
     * Base method that show if actor is still alive
     *
     * @return if actor alive
     */
    @Override
    public boolean isAlive() {
        return false;
    }

    /**
     * Method witch show if actor should attack fence on this step
     *
     * @param gameField - object that contains field
     * @return found fence instance
     */
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

    /**
     * Base method that return damage that this actor can make to fence
     *
     * @return base fence damage
     */
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