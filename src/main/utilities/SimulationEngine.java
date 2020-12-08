package main.utilities;

import main.math.Statistics;
import main.math.Vector2d;
import main.elements.Animal;
import main.enums.MoveDirection;
import main.interfaces.IEngine;
import main.interfaces.IPositionChangeObserver;
import main.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private IWorldMap map;
    private List<Animal> animals = new ArrayList<>();
    private Statistics statistics = new Statistics();

    public SimulationEngine(IWorldMap map, int initialNoAnimals, int initialEnergy, int initialNoGrass ){
        this.map = map;
        for(int i=0; i<initialNoAnimals; i+=1){
            animals.add(map.placeAnimal(initialEnergy));
        }
        int n = initialNoGrass/2;
        for(int i=0; i<n; i+=1){
            map.addGrass();
        }

    }

    public void nextDay(int energyFromGrass){
        // moveAnimals + updateEnergy
        map.updateAnimalOrientations();
        moveAnimals();
        map.eatGrass(energyFromGrass);

        // eatGrass + updateEnergy

        removeDeadAnimals();
        int numberOfAnimals = statistics.getNumberOfAnimals(animals);

        System.out.println("Liczba zwierząt: " + Integer.toString(numberOfAnimals));

        int numberOfGrass = map.getNumberOfGrass();
        System.out.println("Liczba traw: " + Integer.toString(numberOfGrass));

        // reproductionOfAnimals



        map.addGrass();


    }

    private void moveAnimals(){
        for (Animal animal: animals){
            animal.moveForward();
            animal.useEnergy(1);
        }

    }


    public void runDays(int numberOfDays, int energyFromGrass){
        for(int i=0; i<numberOfDays; i+=1){
            nextDay(energyFromGrass);
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




    // tutaj mogę mieć next day

}

