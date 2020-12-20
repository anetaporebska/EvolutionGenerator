import main.elements.Animal;
import main.map.WorldMap;
import main.math.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldMapTest {



    @Test
    public void findTheStrongestTest(){
        Vector2d position = new Vector2d(1,1);
        Animal animal1 = new Animal(position, 40, null);
        Animal animal2 = new Animal(position, 30, null);
        Animal animal3 = new Animal(position, 50, null);
        Animal animal4 = new Animal(position, 40, null);
        Animal animal5 = new Animal(position, 10, null);

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(animal1);
        animals.add(animal2);
        animals.add(animal3);
        animals.add(animal4);
        animals.add(animal5);

        WorldMap worldMap = new WorldMap(3,3);

        Animal strongest =  worldMap.findTheStrongest(Integer.MAX_VALUE,animals);
        assertEquals(50, strongest.getAnimalEnergy());
        strongest =  worldMap.findTheStrongest(50,animals);
        assertEquals(40, strongest.getAnimalEnergy());

    }

    @Test
    public void adjustPositionTest(){
        WorldMap worldMap = new WorldMap(10,10);
        assertEquals(new Vector2d(9,9), worldMap.adjustPosition(new Vector2d(-1,-1)));
        assertEquals(new Vector2d(9,0), worldMap.adjustPosition(new Vector2d(-1,0)));
        assertEquals(new Vector2d(0,0), worldMap.adjustPosition(new Vector2d(0,0)));
        assertEquals(new Vector2d(5,0), worldMap.adjustPosition(new Vector2d(5,10)));
    }


    @Test
    public void isOccupiedTest(){
        WorldMap worldMap = new WorldMap(10,10);
        Animal animal1 = new Animal(new Vector2d(1,1), 10, worldMap);
        Animal animal2 = new Animal(new Vector2d(1,1), 10, worldMap);
        worldMap.addAnimal(animal1);
        worldMap.addAnimal(animal2);
        assertEquals(true, worldMap.isOccupied(new Vector2d(1,1)));
        Animal animal3 = new Animal(new Vector2d(2,3), 10, worldMap);
        Animal animal4 = new Animal(new Vector2d(5,5), 10, worldMap);
        worldMap.addAnimal(animal3);
        worldMap.addAnimal(animal4);
        assertEquals(true, worldMap.isOccupied(new Vector2d(2,3)));
        assertEquals(true, worldMap.isOccupied(new Vector2d(5,5)));
        assertEquals(false, worldMap.isOccupied(new Vector2d(6,3)));

    }




}
