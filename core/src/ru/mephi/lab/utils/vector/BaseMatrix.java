package ru.mephi.lab.utils.vector;

import ru.mephi.lab.actor.Actor;

public class BaseMatrix<T> {

    int width;
    int height;

    BaseVector<BaseVector<T>> matrix;

    BaseMatrix(int width, int height) {
        this.width = width;
        this.height = height;

        matrix = new BaseVector<>();

        for (int i = 0; i < height; i++) {
            matrix.add(new BaseVector<T>());
            for (int j = 0; j < width; j++) {
                matrix.get(i).add(null);
            }
        }
    }

    T getCell(int x, int y) {
        return matrix.get(y).get(y);
    }

    void setCell(int x, int y, T value) {
        matrix.get(y).set(x, value);
    }

}
