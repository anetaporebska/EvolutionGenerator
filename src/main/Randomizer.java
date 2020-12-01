package main;

import main.elements.Animal;
import main.elements.Grass;
import main.elements.MapElement;
import main.math.Vector2d;

import java.util.Random;

public class Randomizer {

    private Random random = new Random();

    // wystarczy wylosować liczbę 0-31
    public int randomizeAnimalOrientation(Animal animal, int [] genome){
        return genome[random.nextInt(32)];

    }

    // dla zwierzęcia i dla trawy w dżungi
    // lowerLeft to zawsze (0,0)
    public Vector2d randomizeElementPosition(Vector2d lowerLeft ,Vector2d upperRight){
        // nextInt(int a) generuje losową od 0 włącznie do a wyłącznie
        Vector2d randomPosition = new Vector2d(random.nextInt(upperRight.x - lowerLeft.x +1)+lowerLeft.x,
                                                random.nextInt(upperRight.y - lowerLeft.y +1) + lowerLeft.y);
        return randomPosition;

    }

    // dla trawy poza dżunglą
    public void randomizeGrassSavanna(Vector2d jungleLowerLeft, Vector2d jungleUpperRight, Vector2d WorldMapUpperRight, Vector2d WorldMapLowerLeft){

    }




}
