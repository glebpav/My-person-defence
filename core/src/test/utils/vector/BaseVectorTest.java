package utils.vector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mephi.lab.utils.vector.BaseVector;

import java.util.ArrayList;


public class BaseVectorTest {

    static BaseVector<Integer> baseVector;

    @BeforeAll
    static void prepareData() {
        baseVector = new BaseVector<>();
    }

    @Test
    void getSize() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        Assertions.assertEquals(vector.getSize(), 1);
    }

    @Test
    void add_remove() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        Assertions.assertEquals(vector.getSize(), 3);
        vector.remove(0);
        Assertions.assertEquals(vector.getSize(), 2);
    }

    @Test
    void containsAll() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        Assertions.assertTrue(vector.containsAll(list));
    }

    @Test
    void addAll() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        vector.addAll(list);

        Assertions.assertEquals(vector.getSize(), 5);
    }

    @Test
    void removeAll() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);

        vector.removeAll(list);

        Assertions.assertEquals(vector.getSize(), 2);
    }

    @Test
    void retainAll() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);

        Assertions.assertTrue(vector.retainAll(list));
    }

    @Test
    void clear() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        vector.clear();

        Assertions.assertEquals(vector.size(), 0);
    }

    @Test
    void set() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        vector.set(1, 1);

        Assertions.assertEquals(vector.get(1), 1);
    }

    @Test
    void addByIdx() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        vector.add(0, 5);

        Assertions.assertEquals(vector.getSize(), 4);
    }

    @Test
    void indexOf() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        Assertions.assertEquals(vector.indexOf(1), 0);
    }

    @Test
    void lastIndexOf() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        Assertions.assertEquals(vector.indexOf(1), 0);
    }

    @Test
    void listIterator() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        Assertions.assertFalse(vector.listIterator().hasPrevious());

    }

    @Test
    void listIteratorByIdx() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        Assertions.assertFalse(vector.listIterator().hasPrevious());

    }

    @Test
    void subList() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        Assertions.assertEquals(vector.subList(0, 2).size(), 2);
    }

    @Test
    void increaseCapacity() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        Assertions.assertEquals(vector.toString(), "{1,2,3}");
    }

    @Test
    void isEmpty() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        Assertions.assertFalse(vector.isEmpty());
    }

    @Test
    void contains() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        Assertions.assertTrue(vector.contains(1));
    }

    @Test
    void toArray() {
        BaseVector<Integer> vector = new BaseVector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        Assertions.assertEquals(vector.toArray().length, 3);
    }

}
