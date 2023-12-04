package ru.mephi.lab.utils.vector;

import com.google.gson.annotations.SerializedName;

import java.util.*;

public class BaseVector<T> implements List<T> {

     @SerializedName("initialCapacity")
    private static final int INITIAL_CAPACITY = 10;
    @SerializedName("dataVector")
    private T[] dataVector;

    @SerializedName("size")
    private int size = 0;

    public int getSize() {
        return size;
    }

    public BaseVector() {
        // Effective Java; Item 26
        dataVector = (T[]) new Object[INITIAL_CAPACITY];
    }

    public boolean add(T element) {
        if (size == dataVector.length) {
            increaseCapacity();
        }
        dataVector[size++] = element;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < dataVector.length; i++) {
            if (dataVector[i] == o) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T o : c) add(o);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        addAll(c);
        for (int i = size - 1; i >= index; i--) {
            dataVector[i] = dataVector[i - c.size()];
        }
        for (int i = index; i < index + c.size(); i++) {
            dataVector[i] = (T) c.toArray()[i];
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean wasChanged = false;
        for (Object object : c) {
            wasChanged = wasChanged || remove(object);
        }
        return wasChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        BaseVector<Object> bufVector = new <Object>BaseVector();
        for (Object object : c) {
            if (contains(object)) {
                bufVector.add(object);
            }
        }

        if (bufVector.size != size) {
            clear();
            addAll((Collection<? extends T>) bufVector);
            return true;
        }

        return false;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        T object = dataVector[index];
        dataVector[index] = element;
        return object;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }

        add(null);

        for (int i = index + size - 1; i > index; i--) {
            dataVector[i] = dataVector[i - 1];
        }

        dataVector[index] = element;
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

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == dataVector[i]) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o == dataVector[i]) return i;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new BaseVectorIterator<>(this);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new BaseVectorIterator<>(this, index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (toIndex > size || fromIndex < 0) {
            throw new IndexOutOfBoundsException();
        }

        BaseVector<T> subVector = new BaseVector<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subVector.add(dataVector[i]);
        }

        return subVector;
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
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object object : dataVector) {
            if (o == object) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new BaseVectorIterator(this);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = get(i);
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            T1[] array = (T1[]) new Object[size];
            for (int i = 0; i < size; i++) {
                array[i] = (T1) get(i);
            }
            return array;
        } else {
            for (int i = 0; i < size; i++) {
                a[i] = (T1) get(i);
            }
            return a;
        }
    }
}