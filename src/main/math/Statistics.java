package main.math;

import main.elements.Animal;

import java.util.List;

public class Statistics {

    public int getNumberOfAnimals(List<Animal> animals){
        return animals.toArray().length;
    }

}
