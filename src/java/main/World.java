package main;

import main.map.WorldParameters;
import main.visualization.LaunchPage;
import main.visualization.SimulationWindow;

public class World {

    public static void main(String[] args) {

        // launch application with launch page and set parameters

        new LaunchPage();



        // read parameters from file and launch application
        /*
        WorldParameters worldParameters = new WorldParameters();
        worldParameters.readParameters();
        new Thread (new SimulationWindow(worldParameters)).start();
        new Thread (new SimulationWindow(worldParameters)).start();
        */
    }

}




