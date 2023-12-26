package ru.mephi.lab.utils.vector;

import com.google.gson.annotations.SerializedName;

public class BaseMatrix<T> {

    @SerializedName("width")
    public int width;
    @SerializedName("height")
    int height;

    @SerializedName("matrix")
    public BaseVector<BaseVector<T>> matrix;

    public BaseMatrix(int width, int height) {
        this.width = width;
        this.height = height;

        matrix = new BaseVector<>();

        for (int i = 0; i < width; i++) {
            matrix.add(new BaseVector<>());
            for (int j = 0; j < height; j++) {
                matrix.get(i).add(null);
            }
        }
    }

    public T getCell(int x, int y) {
        return matrix.get(x).get(y);
    }

    public void setCell(int x, int y, T value) {
        matrix.get(x).set(y, value);
    }

}
