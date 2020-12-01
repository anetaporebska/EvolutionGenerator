import main.enums.MapDirection;
import main.math.Vector2d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class Vector2dTest {

    Vector2d v_1_2 = new Vector2d(1,2);
    Vector2d v_1_3 = new Vector2d(1,3);
    Vector2d v_2_3 = new Vector2d(2,3);
    Vector2d v_3_5 = new Vector2d(3,5);
    Vector2d v_3_2 = new Vector2d(3,2);
    Vector2d v_2_2 = new Vector2d(2,2);
    Vector2d v__3__2 = new Vector2d(-3,-2);
    Vector2d v_2_5 = new Vector2d(2,5);
    Vector2d v_3_6 = new Vector2d(3,6);
    Vector2d v__2_1 = new Vector2d(-2,1);
    Vector2d v_0__1 = new Vector2d(0,-1);
    Vector2d v_1_0 = new Vector2d(1,0);
    Vector2d v__4__5 = new Vector2d(-4,-5);
    Vector2d v_0_1 = new Vector2d(0,1);
    Vector2d v__1__2 = new Vector2d(-1,-2);
    Vector2d v__1__3 = new Vector2d(-1,-3);
    Vector2d v__2_0 = new Vector2d(-2,0);
    Vector2d v_2_0 = new Vector2d(2,0);



    @Test
    public void testEquals(){
        MapDirection N = MapDirection.NORTH;
        assertEquals(false, v_1_2.equals(N));
        assertEquals(true,v_1_2.equals(v_1_2));
        assertEquals(false,v_1_2.equals(v_1_3));
    }

    @Test
    public void testToString(){
        assertEquals("(1,2)",v_1_2.toString());
    }

    @Test
    public void testPrecedes(){
        assertEquals(true,v_1_2.precedes(this.v_2_3));
        assertEquals(false,v_2_3.precedes(this.v_1_2));
        assertEquals(false,v_1_3.precedes(this.v_1_2));
        assertEquals(true,v_1_2.precedes(this.v_1_3));
    }

    @Test
    public void testFollows(){
        assertEquals(false,v_1_2.follows(v_2_3));
        assertEquals(true,v_2_3.follows(v_1_2));
        assertEquals(true,v_1_3.follows(v_1_2));
        assertEquals(false,v_1_2.follows(v_1_3));

    }

    @Test
    public void testUpperRight(){
        assertEquals(v_2_3,v_1_2.upperRight(v_2_3));
        assertEquals(v_3_5,v_1_3.upperRight(v_3_5));
        assertEquals(v_3_5,v_3_5.upperRight(v_2_3));
    }

    @Test
    public void testLowerLeft(){
        assertEquals(v_2_2,v_3_2.lowerLeft(v_2_3));
        assertEquals(v_1_2,v_1_3.lowerLeft(v_3_2));
        assertEquals(v_2_2,v_3_2.lowerLeft(v_2_3));
    }

    @Test
    public void testAdd(){
        assertEquals(v_2_5,v_1_2.add(v_1_3));
        assertEquals(v_3_6,v_2_3.add(v_1_3));
        assertEquals(v__2_1,v__3__2.add(v_1_3));
        assertEquals(v_2_5,v_1_3.add(v_1_2));
    }
    @Test
    public void testSubtract(){
        assertEquals(v_0__1,v_1_2.subtract(v_1_3));
        assertEquals(v_1_0,v_2_3.subtract(v_1_3));
        assertEquals(v__4__5,v__3__2.subtract(v_1_3));
        assertEquals(v_0_1,v_1_3.subtract(v_1_2));
    }

    @Test
    public void testOpposite() {
        assertEquals(v__1__2, v_1_2.opposite());
        assertEquals(v__1__3, v_1_3.opposite());
        assertEquals(v__2_0, v_2_0.opposite());
        assertEquals(v_3_2, v__3__2.opposite());
    }

}
