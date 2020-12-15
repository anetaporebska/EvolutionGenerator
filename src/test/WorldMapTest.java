import main.World;
import main.elements.Animal;
import main.maps.WorldMap;
import main.maps.WorldParameters;
import main.math.Vector2d;
import main.utilities.SimulationEngine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldMapTest {


    //TODO sprawdzić czy zwierzęta nie wychodzą poza mapę

    @Test
    public void insideBoundariesTest(){


        WorldParameters worldParameters = new WorldParameters();
        worldParameters.setWorldWidth(10);
        worldParameters.setWorldHeight(10);
        worldParameters.setJungleHeight(4);
        worldParameters.setJungleWidth(4);
        worldParameters.setInitialNoAnimals(10);
        worldParameters.setEnergyFromGrass(4);
        worldParameters.setInitialNoGrass(10);
        worldParameters.setInitialEnergy(200);


    }


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

        Animal strongest =  worldMap.findTheStrongest(10000,animals);
        assertEquals(50, strongest.getAnimalEnergy());
        strongest =  worldMap.findTheStrongest(50,animals);
        assertEquals(40, strongest.getAnimalEnergy());





    }


    //TODO

}
