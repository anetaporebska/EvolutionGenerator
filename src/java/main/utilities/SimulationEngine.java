package main.utilities;

import main.elements.Animal;
import main.interfaces.IEngine;
import main.interfaces.IWorldMap;
import main.map.WorldParameters;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private final IWorldMap map;
    private final List<Animal> animals = new ArrayList<>();


    public SimulationEngine(IWorldMap map) {
        this.map = map;
        this.map.addObserver(this);

        int n = WorldParameters.initialNoGrass / 2;
        for (int i = 0; i < n; i += 1) {
            map.addGrass();
        }
        for (int i = 0; i < WorldParameters.initialNoAnimals; i += 1) {
            animals.add(map.placeAnimal(WorldParameters.initialEnergy));
        }
        this.map.initializeStatisticsTree();
    }


    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void nextDay() {
        int energyFromGrass = WorldParameters.energyFromGrass;
        removeDeadAnimals();
        map.updateAnimalOrientations();
        moveAnimals();
        map.eatGrass(energyFromGrass);
        map.reproductionOfAnimals();
        map.addGrass();
    }

    private void moveAnimals() {
        for (Animal animal : animals) {
            animal.useEnergy(1);
            animal.moveForward();
            animal.changeAge();

        }
    }

    private void removeDeadAnimals() {
        List<Animal> animalsToDelete = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.checkIfDead()) {
                map.removeAnimal(animal);
                animalsToDelete.add(animal);
            }
        }
        for (Animal animal : animalsToDelete) {
            animals.remove(animal);
        }
        animalsToDelete.clear();
    }
}

