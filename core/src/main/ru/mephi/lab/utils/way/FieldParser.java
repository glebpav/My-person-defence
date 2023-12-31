package ru.mephi.lab.utils.way;

import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.enemy.EnemyType;
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.cell.CellType;
import ru.mephi.lab.level.GameField;
import ru.mephi.lab.level.GameSession;
import ru.mephi.lab.utils.vector.BaseMatrix;

public class FieldParser {

    public static int[][] parseField(GameSession gameSession, EnemyType enemyType) {

        GameField field = gameSession.field;
        int width = gameSession.field.fieldWidth;
        int height = gameSession.field.fieldHeight;

        int[][] connectionMatrix = new int[width * height][height * width];

        Position thisIdx, topIdx, leftIdx, rightIdx, bottomIdx;
        int thisNodeIdx, connectedNodeIdx;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                thisIdx = new Position(j, i);
                topIdx = new Position(j, i - 1);
                bottomIdx = new Position(j, i + 1);
                leftIdx = new Position(j - 1, i);
                rightIdx = new Position(j + 1, i);

                thisNodeIdx = i * width + j;

                if (topIdx.y >= 0) {
                    connectedNodeIdx = (int) (width * topIdx.y + topIdx.x);
                    connectionMatrix[thisNodeIdx][connectedNodeIdx] = getWeight(
                            getCellType(field.field, (int) thisIdx.x, (int) thisIdx.y),
                            getCellType(field.field, (int) topIdx.x, (int) topIdx.y),
                            enemyType
                    );
                }

                if (bottomIdx.y < height) {
                    connectedNodeIdx = (int) (width * bottomIdx.y + bottomIdx.x);
                    connectionMatrix[thisNodeIdx][connectedNodeIdx] = getWeight(
                            getCellType(field.field, (int) thisIdx.x, (int) thisIdx.y),
                            getCellType(field.field, (int) bottomIdx.x, (int) bottomIdx.y),
                            enemyType
                    );
                }

                if (leftIdx.x >= 0) {
                    connectedNodeIdx = (int) (width * leftIdx.y + leftIdx.x);
                    connectionMatrix[thisNodeIdx][connectedNodeIdx] = getWeight(
                            getCellType(field.field, (int) thisIdx.x, (int) thisIdx.y),
                            getCellType(field.field, (int) leftIdx.x, (int) leftIdx.y),
                            enemyType
                    );
                }

                if (rightIdx.x < width) {
                    connectedNodeIdx = (int) (width * rightIdx.y + rightIdx.x);
                    connectionMatrix[thisNodeIdx][connectedNodeIdx] = getWeight(
                            getCellType(field.field, (int) thisIdx.x, (int) thisIdx.y),
                            getCellType(field.field, (int) rightIdx.x, (int) rightIdx.y),
                            enemyType
                    );
                }

            }
        }

        return connectionMatrix;

    }

    @SuppressWarnings("NewApi")
    private static CellType getCellType(BaseMatrix<Cell> matrix, int x, int y) {
        Cell cell = matrix.getCell(x, y);
        CellType finalType = cell.cellType, type;

        for (BaseActor actor : cell.actorsList) {
            if (actor == null) continue;
            type = switch (actor.actorType) {
                case LAIR, TOWER -> CellType.IMPASSABLE_CONSTRUCTION;
                case FENCE -> CellType.FENCE;
                case CASTLE -> CellType.CASTLE;
                default -> CellType.DEFAULT;
            };
            if (type.priority < finalType.priority) finalType = type;
        }

        return finalType;
    }

    public static int getWeight(CellType typeFrom, CellType typeTo, EnemyType enemyType) {
        return switch (enemyType) {
            case LIGHT_INFANTRY -> {
                if (typeTo == CellType.PLANE || typeTo == CellType.CASTLE) yield  1;
                yield 0;
            }
            case AVIATION -> {
                if (typeTo != CellType.MOUNTAIN && typeFrom != CellType.MOUNTAIN) yield 1;
                yield 0;
            }
            case HEAVY_INFANTRY ->  {
                if (typeTo == CellType.PLANE || typeTo == CellType.CASTLE) yield 1;
                if (typeTo == CellType.FENCE) {
                    // System.out.println("fence in get wight");
                    yield 2;
                }
                yield 0;
            }
        };
    }

}
