package main.utilities;

import main.maps.WorldParameters;
import main.elements.Animal;
import main.interfaces.IEngine;
import main.interfaces.IWorldMap;
import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private IWorldMap map;
    private List<Animal> animals = new ArrayList<>();
    private WorldParameters worldParameters;


    public SimulationEngine(IWorldMap map, WorldParameters worldParameters){
        this.map = map;
        this.map.addObserver(this);
        this.worldParameters = worldParameters;

        int n = worldParameters.getInitialNoGrass()/2;
        for(int i=0; i<n; i+=1){
            map.addGrass();
        }
        for(int i=0; i< worldParameters.getInitialNoAnimals(); i+=1){
            animals.add(map.placeAnimal(worldParameters.getInitialEnergy()));
        }
        this.map.initializeStatisticsTree();

    }


    public void addAnimal(Animal animal){
        animals.add(animal);
    }

    public void nextDay(){
        int energyFromGrass = worldParameters.getEnergyFromGrass();
        removeDeadAnimals();
        map.updateAnimalOrientations();
        moveAnimals();
        map.eatGrass(energyFromGrass);
        map.reproductionOfAnimals();
        map.addGrass();
    }

    private void moveAnimals(){
        for (Animal animal: animals){
            animal.useEnergy(1);
            animal.moveForward();
            animal.changeAge();

        }

    }


    public void runDays(int numberOfDays, int energyFromGrass){
        for(int i=0; i<numberOfDays; i+=1){
            nextDay();
            System.out.println(map.toString());
        }
    }


    public void removeDeadAnimals(){
        List<Animal> animalsToDelete = new ArrayList<>();
        for(Animal animal: animals){
            if (animal.checkIfDead()){
                map.removeAnimal(animal);
                animalsToDelete.add(animal);
            }
        }
        for(Animal animal: animalsToDelete){
            animals.remove(animal);
        }
        animalsToDelete.clear();


    }



}

