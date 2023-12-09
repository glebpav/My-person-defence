package ru.mephi.lab.utils.way;

import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.enemy.EnemyType;
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.level.GameSession;

import java.util.ArrayList;

public class WayProcessor {

    int fieldWidth;
    int fieldHeight;

    int castleIdx;

    int[][] lightInfantryConnectionMatrix;
    int[][] heavyInfantryConnectionMatrix;
    int[][] aviationConnectionMatrix;

    public WayProcessor(int fieldHeight, int fieldWidth, GameSession gameSession) {

        this.fieldHeight = fieldHeight;
        this.fieldWidth = fieldWidth;

        castleIdx = getCastleIdx(gameSession);

        lightInfantryConnectionMatrix = FieldParser.parseField(gameSession, EnemyType.LIGHT_INFANTRY);
        heavyInfantryConnectionMatrix = FieldParser.parseField(gameSession, EnemyType.HEAVY_INFANTRY);
        aviationConnectionMatrix = FieldParser.parseField(gameSession, EnemyType.AVIATION);
    }

    private int getCastleIdx(GameSession gameSession) {
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                Cell cell = gameSession.field.field.getCell(j, i);
                for (BaseActor actor : cell.actorsList) {
                    if (actor.actorType == ActorType.CASTLE) return i * fieldWidth + j;
                }
            }
        }
        return -1;
    }

    public Position getNextPosition(int thisX, int thisY, EnemyType enemyType) {

        System.out.println("here");
        GraphShortestPath graphShortestPath = new GraphShortestPath();
        ArrayList<Integer> shortestPath = graphShortestPath.dijkstra(
                switch (enemyType) {
                    case AVIATION -> aviationConnectionMatrix;
                    case HEAVY_INFANTRY -> heavyInfantryConnectionMatrix;
                    case LIGHT_INFANTRY -> lightInfantryConnectionMatrix;
                },
                thisX + thisY * fieldWidth,
                castleIdx
        );

        System.out.println("Shortest path: " + shortestPath);

        return null;
    }


}
