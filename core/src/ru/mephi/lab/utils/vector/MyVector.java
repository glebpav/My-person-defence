package ru.mephi.lab.utils.vector;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyVector<T> implements Iterable<T>{

    private static final int INITIAL_CAPACITY = 10;
    private T[] dataVector;
    private int size = 0;

    public int getSize(){
        return size;
    }

    public MyVector() {
        // Effective Java; Item 26
        dataVector = (T[]) new Object[INITIAL_CAPACITY];
    }

    public void add(T element) {
        if (size == dataVector.length) {
            increaseCapacity();
        }
        dataVector[size++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        return dataVector[index];
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }

        T removedElement = dataVector[index];
        for (int i = index; i < size - 1; i++) {
            dataVector[i] = dataVector[i + 1];
        }
        size--;

        return removedElement;
    }

    private void increaseCapacity() {
        int newIncreasedCapacity = dataVector.length * 2;
        dataVector = Arrays.copyOf(dataVector, newIncreasedCapacity);
    }

    @Override
    public String toString() {
        StringBuilder outputSting = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            outputSting.append(dataVector[i].toString());
            if (i != size - 1) outputSting.append(",");
        }
        outputSting.append("}");
        return outputSting.toString();
    }

    @Override
    public Iterator iterator() {
        return new MyVectorIterator(this);
    }

}