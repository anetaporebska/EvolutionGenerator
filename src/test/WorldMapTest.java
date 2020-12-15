import main.World;
import main.maps.WorldMap;
import main.maps.WorldParameters;
import main.utilities.SimulationEngine;
import org.junit.jupiter.api.Test;
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


    //TODO

}
