package ru.mephi.lab.utils.vector;

import java.util.ListIterator;

public class BaseVectorIterator<T> implements ListIterator<T> {

    /**
     * Index of current element in iterator
     */
    int cursor;
    /**
     * Vector by witch we iterates
     */
    BaseVector<T> vector;

    /**
     * Base constructor of BaseIterator
     * @param vector by witch we iterates
     */
    BaseVectorIterator(BaseVector<T> vector) {
        this.vector = vector;
    }

    /**
     * Constructor to BaseVectorIterator witch starts from element with
     * required index
     * @param vector by witch we iterates
     * @param index starting element in this iterator
     */
    BaseVectorIterator(BaseVector<T> vector, int index) {
        this(vector);
        cursor = index;
    }

    /**
     * Method witch shows if iterator has next element
     * @return true in case cursor is smaller that size of vector
     */
    @Override
    public boolean hasNext() {
        return cursor < vector.getSize();
    }

    /**
     * Method witch gives next element
     * @return next element from array
     */
    @Override
    public T next() {
        return getNextElement();
    }

    /**
     * Method that shows if iterator has previous element
     * @return if cursor bigger than zero
     */
    @Override
    public boolean hasPrevious() {
        return cursor > 0;
    }

    /**
     * Method withs gives previous element
     * @return previous element or null in case if there is no previous
     */
    @Override
    public T previous() {
        if (cursor < vector.size() && cursor > 0) return vector.get(cursor - 1);
        return null;
    }

    /**
     * Method witch return index of next element
     * @return current state of cursor
     */
    @Override
    public int nextIndex() {
        return cursor;
    }

    /**
     * Method witch gives index of previous element
     * @return current state of cursor - 1
     */
    @Override
    public int previousIndex() {
        return cursor - 1;
    }

    /**
     * Method witch removes current element from array
     */
    @Override
    public void remove() {
        if (cursor == 0) vector.remove(0);
        else vector.remove(cursor - 1);
    }

    /**
     * Method witch set this element with new value
     * @param t the element with which to replace the last element returned by
     *          {@code next} or {@code previous}
     */
    @Override
    public void set(T t) {
        vector.set(cursor, t);
    }

    /**
     * Method witch add element to vector
     * @param t the element to insert
     */
    @Override
    public void add(T t) {
        vector.add(t);
    }

    /**
     * Method witch gives next element in iterator
     * @return next element or null in case no next element
     */
    private T getNextElement() {
        int current = cursor;

        cursor = current + 1;
        if (vector.getSize() < current) return vector.get(current);
        return null;
    }
}
