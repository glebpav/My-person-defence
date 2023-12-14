package ru.mephi.lab.threads;

import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.enemy.Enemy;
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.level.GameSession;

import java.util.ArrayList;

public class StepThread extends Thread {

    GameSession gameSession;
    boolean hasAnyEnemyFound;
    double sumCastleDamage;
    ArrayList<BaseActor> removedActors;

    int countOfThreads;
    int threadIdx;

    public StepThread(GameSession gameSession, int countOfThreads, int threadIdx) {
        this.gameSession = gameSession;
        hasAnyEnemyFound = false;
        sumCastleDamage = 0;

        removedActors = new ArrayList<>();

        this.countOfThreads = countOfThreads;
        this.threadIdx = threadIdx;
    }

    @Override
    public void run() {

        for (int x = 0; x < gameSession.field.fieldHeight; x++) {
            for (int y = 0; y < gameSession.field.fieldWidth; y++) {

                int cellIdx = y * gameSession.field.fieldWidth + x;
                if (cellIdx % countOfThreads != threadIdx) continue;

                Cell cell = gameSession.field.field.getCell(x, y);
                Position deleteFencePosition = null;
                for (int i = 0; i < cell.actorsList.size(); i++) {
                    BaseActor actor = cell.actorsList.get(i);
                    if (actor.actorType == ActorType.ENEMY) {

                        boolean didAttack = false;
                        hasAnyEnemyFound = true;

                        switch (((Enemy) actor).enemyType) {
                            case LIGHT_INFANTRY, AVIATION, HEAVY_INFANTRY -> {
                                if (actor.shouldAttack(
                                        (int) gameSession.wayProcessor.castlePosition.x,
                                        (int) gameSession.wayProcessor.castlePosition.y)
                                ) {
                                    sumCastleDamage += actor.makeDamage();
                                    didAttack = true;
                                }
                                BaseActor fence = actor.shouldAttackFence(gameSession.field);
                                if (fence != null) {
                                    double damageFence = actor.makeDamageFence();
                                    if (fence.getDamage(damageFence)) {
                                        removedActors.add(fence);
                                        fence.fieldPosition.setPosition(actor.fieldPosition.x, actor.fieldPosition.y);
                                        deleteFencePosition = fence.fieldPosition;
                                    }
                                }
                            }
                        }

                        if (didAttack) {
                            removedActors.add(cell.actorsList.get(i));
                            cell.actorsList.remove(i);
                            i -= 1;
                        } else {
                            ((Enemy) actor).makeStep(gameSession.wayProcessor);
                        }

                    }
                }

                boolean wasFieldModified = deleteFencePosition != null;

                if (wasFieldModified) {

                    ArrayList<BaseActor> actors = gameSession.field.getCeilActor(
                            (int) deleteFencePosition.x,
                            (int) deleteFencePosition.y
                    );

                    for (int i = 0; i < actors.size(); i++) {
                        if (actors.get(i).actorType == ActorType.FENCE) {
                            actors.remove(i);
                            i -= 1;
                        }
                    }
                }

                if (wasFieldModified) {
                    gameSession.wayProcessor.updateConnectionsMatrix(gameSession);
                }
            }
        }

    }
}
