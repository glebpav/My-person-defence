package test1.utils.vector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mephi.lab.utils.vector.BaseMatrix;

public class BaseMatrixTest {


    static BaseMatrix<Integer> baseMatrix;

    @BeforeAll
    static void prepareData() {
        baseMatrix = new BaseMatrix<Integer>(10, 10);
    }


    @Test
    void getCell_test() {

        baseMatrix.setCell(0, 0, 10);
        Assertions.assertEquals(baseMatrix.getCell(0, 0), 10);

    }

}