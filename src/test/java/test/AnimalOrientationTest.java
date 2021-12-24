package test;

import main.enums.AnimalOrientation;
import main.math.Vector2d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalOrientationTest {

    AnimalOrientation N = AnimalOrientation.NORTH;
    AnimalOrientation NE = AnimalOrientation.NORTHEAST;
    AnimalOrientation E = AnimalOrientation.EAST;
    AnimalOrientation SE = AnimalOrientation.SOUTHEAST;
    AnimalOrientation S = AnimalOrientation.SOUTH;
    AnimalOrientation SW = AnimalOrientation.SOUTHWEST;
    AnimalOrientation W = AnimalOrientation.WEST;
    AnimalOrientation NW = AnimalOrientation.NORTHWEST;

    @Test
    public void nextTest(){
        assertEquals(NE, N.next());
        assertEquals(E, NE.next());
        assertEquals(SE, E.next());
        assertEquals(S, SE.next());
        assertEquals(SW, S.next());
        assertEquals(W, SW.next());
        assertEquals(NW, W.next());
        assertEquals(N, NW.next());

    }


    @Test
    public void toUnitVectorTest(){
        assertEquals(new Vector2d(0,1), N.toUnitVector());
        assertEquals(new Vector2d(1,1), NE.toUnitVector());
        assertEquals(new Vector2d(1,0), E.toUnitVector());
        assertEquals(new Vector2d(1,-1), SE.toUnitVector());
        assertEquals(new Vector2d(0,-1), S.toUnitVector());
        assertEquals(new Vector2d(-1,-1), SW.toUnitVector());
        assertEquals(new Vector2d(-1,0), W.toUnitVector());
        assertEquals(new Vector2d(-1,1), NW.toUnitVector());

    }

    @Test
    public void intToOrientationTest(){
        assertEquals(N,AnimalOrientation.intToOrientation(0));
        assertEquals(NE,AnimalOrientation.intToOrientation(1));
        assertEquals(E,AnimalOrientation.intToOrientation(2));
        assertEquals(SE,AnimalOrientation.intToOrientation(3));
        assertEquals(S,AnimalOrientation.intToOrientation(4));
        assertEquals(SW,AnimalOrientation.intToOrientation(5));
        assertEquals(W,AnimalOrientation.intToOrientation(6));
        assertEquals(NW,AnimalOrientation.intToOrientation(7));



    }


}
