package main.utilities;

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


        // reproductionOfAnimals
        // addGrass()
        // removeDeadAnimals
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







    // tutaj mogę mieć next day

}

