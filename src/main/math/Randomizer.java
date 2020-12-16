package main.math;

import main.elements.Animal;
import main.enums.AnimalOrientation;

import java.util.Random;

public class Randomizer {

    private Random random = new Random();

    public int randomizeAnimalOrientation(Animal animal, int [] genome){
        return genome[random.nextInt(32)];

    }

    public Vector2d randomizeElementPosition(Vector2d lowerLeft ,Vector2d upperRight){
        Vector2d randomPosition = new Vector2d(random.nextInt(upperRight.x - lowerLeft.x +1)+lowerLeft.x,
                                                random.nextInt(upperRight.y - lowerLeft.y +1) + lowerLeft.y);
        return randomPosition;

    }

    public int randomParent(){
        return random.nextInt(2);
    }

    public int randomIndex(){
        return random.nextInt(32);
    }

    public AnimalOrientation randomOrientation(){
        int x = random.nextInt(8);
        return AnimalOrientation.intToOrientation(x);

    }




}
