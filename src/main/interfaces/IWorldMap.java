package main.interfaces;

import main.elements.Animal;
import main.math.Vector2d;

public interface IWorldMap {

    boolean canMoveTo(Vector2d position);

    boolean isOccupied(Vector2d position);

    Object objectAt(Vector2d position);

    Animal placeAnimal(int initialEnergy);

    void addGrass();

    String toString();

    void updateAnimalOrientations();

    void eatGrass(int energyFromGrass);
    void removeAnimal(Animal animal);

    void reproductionOfAnimals();

    void addObserver(IEngine engine);

    int getWorldWidth();
    int getWorldHeight();

    void notifyMapPanel(Vector2d position);

    void updateStatistics();

    void initializeStatisticsTree();
    String displayAnimalGenome(Vector2d position);
    void removeToFollow();
    void checkIfAlive();

    boolean dominantAnimal(Vector2d position);

    Vector2d adjustPosition(Vector2d position);




}