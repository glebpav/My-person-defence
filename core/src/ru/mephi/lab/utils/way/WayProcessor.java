package ru.mephi.lab.utils.way;

import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.enemy.EnemyType;
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.level.GameSession;

import java.util.ArrayList;
import java.util.Arrays;

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

    @SuppressWarnings("NewApi")
    public Position getNextPosition(int thisX, int thisY, EnemyType enemyType) {
        Arrays.stream(lightInfantryConnectionMatrix).forEach(ints -> {
            System.out.println(Arrays.toString(ints));
        });

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

        System.out.println("shortest path: " + shortestPath);

        if (shortestPath == null || shortestPath.isEmpty()) return null;

        return new Position(shortestPath.get(0) % fieldWidth, (float) (shortestPath.get(0) / fieldWidth));
    }


}
