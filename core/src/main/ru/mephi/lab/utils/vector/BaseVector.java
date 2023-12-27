package ru.mephi.lab.utils.vector;

import com.google.gson.annotations.SerializedName;

import java.util.*;

/**
 * Class that realize base vector with implemented list interface
 * @param <T>
 */
public class BaseVector<T> implements List<T> {

    /**
     * Initial capacity that has vector in moment of creation
     */
    @SerializedName("initialCapacity")
    private static final int INITIAL_CAPACITY = 10;
    /**
     * Base array where information is stored
     */
    @SerializedName("dataVector")
    private T[] dataVector;

    /**
     * Real size of array witch increase when user add data
     */
    @SerializedName("size")
    private int size = 0;

    /**
     * Method that return size of used array part
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Base constructor for custom vector class
     */
    public BaseVector() {
        // Effective Java; Item 26
        dataVector = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Method witch is used to add item to array
     * @param element element whose presence in this collection is to be ensured
     * @return ture in case of successful addition
     */
    public boolean add(T element) {
        if (size == dataVector.length) {
            increaseCapacity();
        }
        dataVector[size++] = element;
        return true;
    }

    /**
     * Method witch used to remove object from array
     * @param o element to be removed from this list, if present
     * @return ture in case if object was found and deleted
     */
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

    /**
     * Check if all objects from c are in array
     * @param c collection to be checked for containment in this list
     * @return true in case if all objects are in array
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    /**
     * Add all elements from c to array
     * @param c collection containing elements to be added to this collection
     * @return in case if all objects from c were added
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T o : c) add(o);
        return true;
    }


    /**
     * Add all items form c by index to array
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c collection containing elements to be added to this list
     * @return if all object were added successful
     */
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

    /**
     * Removes all elements in c from array
     * @param c collection containing elements to be removed from this list
     * @return true if all elements were deleted successful
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean wasChanged = false;
        for (Object object : c) {
            wasChanged = wasChanged || remove(object);
        }
        return wasChanged;
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection (optional operation).
     * @param c collection containing elements to be retained in this list
     * @return true in case if all objects were retained
     */
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

    /**
     * Make size of this array as zero
     */
    @Override
    public void clear() {
        size = 0;
    }

    /**
     * Set element on index by new value - element
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return deleted object
     */
    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        T object = dataVector[index];
        dataVector[index] = element;
        return object;
    }

    /**
     * Add element in array by index
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     */
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

    /**
     * Get element by index
     * @param index index of the element to return
     * @return required element
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        return dataVector[index];
    }

    /**
     * Remove element from array by index
     * @param index the index of the element to be removed
     * @return removed element
     */
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

    /**
     * Get index of element in array
     * @param o element to search for
     * @return index of element or -1 in case if element is not found
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == dataVector[i]) return i;
        }
        return -1;
    }

    /**
     * Get last entry index of this element in this array
     * @param o element to search for
     * @return index of element or -1 in case if element is not found
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o == dataVector[i]) return i;
        }
        return -1;
    }

    /**
     * Method witch is used to get list iterator
     * @return listIterator object
     */
    @Override
    public ListIterator<T> listIterator() {
        return new BaseVectorIterator<>(this);
    }

    /**
     * Method witch is used to get list iterator starting from index
     * @param index index of the first element to be returned from the list iterator
     * @return listIterator object
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        return new BaseVectorIterator<>(this, index);
    }

    /**
     * Method to get part of current array (subList)
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (exclusive) of the subList
     * @return subList
     */
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

    /**
     * Method that increase capacity of array
     */
    private void increaseCapacity() {
        int newIncreasedCapacity = dataVector.length * 2;
        dataVector = Arrays.copyOf(dataVector, newIncreasedCapacity);
    }

    /**
     * Method that convert current array to string
     * @return array as string
     */
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

    /**
     * Method that gives size of current array
     * @return size of array
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Method that shows if array is empty
     * @return isEmpty
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Method that shows if object o is in current array
     * @param o element whose presence in this list is to be tested
     * @return true in case if array contains object o
     */
    @Override
    public boolean contains(Object o) {
        for (Object object : dataVector) {
            if (o == object) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to get iterator of this array
     * @return instance of BaseVectorIterator
     */
    @Override
    public Iterator iterator() {
        return new BaseVectorIterator(this);
    }

    /**
     * Method that convert this custom array to usual Array
     * @return array of objects
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = get(i);
        }

        return array;
    }

    /**
     * @param a the array into which the elements of this list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return Array with T1 type
     * @param <T1>
     */
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