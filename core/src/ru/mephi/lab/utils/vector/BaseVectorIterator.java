package ru.mephi.lab.utils.vector;

import java.util.ListIterator;

public class BaseVectorIterator<T> implements ListIterator<T> {
    int cursor;
    BaseVector<T> vector;

    BaseVectorIterator(BaseVector<T> vector) {
        this.vector = vector;
    }

    BaseVectorIterator(BaseVector<T> vector, int index) {
        this(vector);
        cursor = index;
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
    public boolean hasPrevious() {
        return cursor > 0;
    }

    @Override
    public T previous() {
        if (cursor < vector.size() && cursor > 0) return vector.get(cursor - 1);
        return null;
    }

    @Override
    public int nextIndex() {
        return cursor;
    }

    @Override
    public int previousIndex() {
        return cursor - 1;
    }

    @Override
    public void remove() {
        if (cursor == 0) vector.remove(0);
        else vector.remove(cursor - 1);
    }

    @Override
    public void set(T t) {
        vector.set(cursor, t);
    }

    @Override
    public void add(T t) {
        vector.add(t);
    }

    private T getNextElement() {
        int current = cursor;

        cursor = current + 1;
        return vector.get(current);
    }
}
