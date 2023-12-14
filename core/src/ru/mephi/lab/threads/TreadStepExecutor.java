package ru.mephi.lab.threads;

import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.level.GameSession;

import java.util.ArrayList;

public class TreadStepExecutor {

    GameSession gameSession;
    public boolean hasAnyEnemyFound;
    public double sumCastleDamage;
    public ArrayList<Actor> removedActors;

    public TreadStepExecutor(GameSession gameSession) {
        this.gameSession = gameSession;
        this.hasAnyEnemyFound = false;
        this.sumCastleDamage = 0;
        this.removedActors = new ArrayList<>();
    }

    @SuppressWarnings("NewApi")
    public boolean start(int countOfTreads) {

        if (countOfTreads <= 0) return false;

        ArrayList<StepThread> stepThreadsArray = new ArrayList<>();

        for (int i = 0; i <= countOfTreads; i++) {
            StepThread stepThread = new StepThread(gameSession, countOfTreads, i);
            stepThreadsArray.add(stepThread);
            stepThread.start();
        }

        try {
            for (StepThread stepThread : stepThreadsArray) {
                stepThread.join();
            }

            stepThreadsArray.forEach(stepThread -> {
                sumCastleDamage += stepThread.sumCastleDamage;
                hasAnyEnemyFound = hasAnyEnemyFound || stepThread.hasAnyEnemyFound;
                removedActors.addAll(stepThread.removedActors);
            });

            return true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
