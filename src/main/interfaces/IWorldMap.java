package main.interfaces;

import main.elements.Animal;
import main.math.Vector2d;

public interface IWorldMap {

    boolean canMoveTo(Vector2d position);


    //boolean place(Animal animal);

    boolean isOccupied(Vector2d position);

    Object objectAt(Vector2d position);

    Vector2d getWorldMapUpperRight();
    Vector2d getWorldMapLowerLeft();

    Animal placeAnimal(int initialEnergy);

    void addGrass();

    String toString();

    void updateAnimalOrientations();

    void eatGrass(int energyFromGrass);
    void removeAnimal(Animal animal);
    int getNumberOfGrass();

    void reproductionOfAnimals();

    void addObserver(IEngine engine);

    int getWorldWidth();
    int getWorldHeight();

    void notifyMapPanel(Vector2d position);

    void updateStatistics();

    void initializeStatisticsTree();




}