import main.enums.MapDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MapDirectionTest {

    MapDirection N = MapDirection.NORTH;
    MapDirection S = MapDirection.SOUTH;
    MapDirection E = MapDirection.EAST;
    MapDirection W = MapDirection.WEST;

    @Test
    public void testNext(){
        assertEquals(MapDirection.EAST, N.next());
        assertEquals(MapDirection.WEST, S.next());
        assertEquals(MapDirection.SOUTH, E.next());
        assertEquals(MapDirection.NORTH, W.next());
    }

    @Test
    public void testPrevious(){
        assertEquals(MapDirection.WEST, N.previous());
        assertEquals(MapDirection.EAST, S.previous());
        assertEquals(MapDirection.NORTH, E.previous());
        assertEquals(MapDirection.SOUTH, W.previous());
    }

}
