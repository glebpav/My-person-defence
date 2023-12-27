package ru.mephi.lab.utils.vector;

import com.google.gson.annotations.SerializedName;

public class BaseMatrix<T> {

    /**
     * field witch contains width of matrix
     */
    @SerializedName("width")
    public int width;
    /**
     * field witch contains height of matrix
     */
    @SerializedName("height")
    int height;

    /**
     * 2D array witch stores matrix elements
     */
    @SerializedName("matrix")
    public BaseVector<BaseVector<T>> matrix;

    /**
     * Base constructor of matrix
     * @param width - width of matrix
     * @param height - height of matrix
     */
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

    /**
     * Method witch used to get value form matrix cell
     * @param x - column index
     * @param y - row index
     * @return matrix cell
     */
    public T getCell(int x, int y) {
        return matrix.get(x).get(y);
    }

    /**
     * Method witch used to set required cell with value
     * @param x - column index
     * @param y - row index
     * @param value - new value of required cell
     */
    public void setCell(int x, int y, T value) {
        matrix.get(x).set(y, value);
    }

}
