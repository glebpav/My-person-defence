package utils.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.utils.geometry.GeometryHelper;

public class GeometryHelperTest {

    @Test
    void convertCoords() {
        Position position = GeometryHelper.convertCoords(0, 0);

        Assertions.assertEquals(position.x, 0);
        Assertions.assertEquals(position.y, 0);
    }

    @Test
    void convertCoordsToCellCenter() {
        Position position = GeometryHelper.convertCoordsToCellCenter(0, 0);

        Assertions.assertEquals(position.x, 32);
        Assertions.assertEquals(position.y, 78);
    }

}
