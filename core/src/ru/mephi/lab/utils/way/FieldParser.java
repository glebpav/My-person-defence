package ru.mephi.lab.utils.way;


import ru.mephi.lab.level.GameSession;

public class FieldParser {

    public static int[][] parseField(GameSession gameSession) {

        int width = gameSession.field.fieldWidth;
        int height = gameSession.field.fieldHeight;

        int[][] fieldMatrix = new int[width * height][height * width];

        int thisIdx, topIdx, leftIdx, rightIdx, bottomIdx;

        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {

                thisIdx = i * width + height;
                topIdx = (i - 1) * width + height;
                bottomIdx = (i + 1) * width + height;
                leftIdx = i *width + height - 1;
                rightIdx = i *width + height + 1;

                // TODO: continue here
                //if (topIdx >= 0)

            }
        }

        return null;

    }

}
