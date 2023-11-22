package ru.mephi.lab.utils.vector;

import java.util.Iterator;
import java.util.function.Consumer;

public class MyVectorIterator<T> implements Iterator<T> {
    int cursor;
    MyVector <T>vector;

    MyVectorIterator(MyVector<T> vector) {
        this.vector = vector;
    }

    @Override
    public boolean hasNext() {
        return cursor < vector.getSize();
    }

    @Override
    public T next() {
        return getNextElement();
    }

    @Override
    public void remove() {
        if (cursor == 0) vector.remove(0);
        else vector.remove(cursor - 1);
    }

    private T getNextElement() {
        int current = cursor;

        cursor = current + 1;
        return vector.get(current);
    }
}
