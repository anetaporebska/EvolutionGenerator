package main.utilities;

import main.WorldParameters;
import main.math.Statistics;
import main.elements.Animal;
import main.interfaces.IEngine;
import main.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private IWorldMap map;
    private List<Animal> animals = new ArrayList<>();
    private Statistics statistics = new Statistics();
    private WorldParameters worldParameters;

    public SimulationEngine(IWorldMap map, int initialNoAnimals, int initialEnergy, int initialNoGrass ){
        this.map = map;
        this.map.addObserver(this);
        for(int i=0; i<initialNoAnimals; i+=1){
            animals.add(map.placeAnimal(initialEnergy));
        }
        int n = initialNoGrass/2;
        for(int i=0; i<n; i+=1){
            map.addInitialGrass();
        }

    }

    public SimulationEngine(IWorldMap map, WorldParameters worldParameters){
        this.map = map;
        this.map.addObserver(this);
        this.worldParameters = worldParameters;
        for(int i=0; i< worldParameters.getInitialNoAnimals(); i+=1){
            animals.add(map.placeAnimal(worldParameters.getInitialEnergy()));
        }
        int n = worldParameters.getInitialNoGrass()/2;
        for(int i=0; i<n; i+=1){
            map.addInitialGrass();
        }

    }


    public void addAnimal(Animal animal){
        animals.add(animal);
    }

    public void nextDay(){
        int energyFromGrass = worldParameters.getEnergyFromGrass();
        removeDeadAnimals();

        // moveAnimals + updateEnergy
        map.updateAnimalOrientations();
        moveAnimals();
        map.eatGrass(energyFromGrass);

        // eatGrass + updateEnergy


        int numberOfAnimals = statistics.getNumberOfAnimals(animals);

        System.out.println("Liczba zwierząt: " + Integer.toString(numberOfAnimals));

        int numberOfGrass = map.getNumberOfGrass();
        System.out.println("Liczba traw: " + Integer.toString(numberOfGrass));

        // reproductionOfAnimals

        map.reproductionOfAnimals();

        map.addGrass();

        // TODO actualize visualization


    }

    private void moveAnimals(){
        for (Animal animal: animals){
            animal.useEnergy(1);
            animal.moveForward();

        }

    }


    public void runDays(int numberOfDays, int energyFromGrass){
        for(int i=0; i<numberOfDays; i+=1){
            nextDay();
            System.out.println(map.toString());
        }
    }


    public void removeDeadAnimals(){
        // przejrzeć wszystkie zwierzęta i sprawdzić czy nie trzeba usunąć jakiegoś które jest martwe
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

