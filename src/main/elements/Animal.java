package main.elements;

import main.Randomizer;
import main.enums.AnimalOrientation;
import main.math.Vector2d;
import main.interfaces.IPositionChangeObserver;
import main.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Animal{
    private AnimalOrientation orientation;
    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private Vector2d position;
    protected IWorldMap map;
    // tu potrzebujemy mieć posortowane liczby 0-7, losując ruch na następny dzień losujemy indeks 0-31
    // ma mieć długość 32 -> na początku 8X4 każda orientacja z takim samym prawdopodobieństwem
    private int [] initialGenome = {0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8};
    public int [] genome;
    private int noGenes = 32;

    // na początku będzie zainicjowana jakaś energia, którą określimy, zwierzęta które powstaną po kopulacji, będą dostawać określoną energię
    private int animalEnergy;


    // chciałabym reprezentować każde zwierzę jako kółko jakiegoś koloru z liczbą w środku równą jego aktualnej energii

    Randomizer randomizer = new Randomizer();

    public Animal(Vector2d position, int initialEnergy, IWorldMap map){
        this.map = map;
        this.position = position;
        this.animalEnergy = initialEnergy;
        this.orientation = randomizer.randomOrientation();
        this.genome = initialGenome;
    }


    public Animal(Vector2d position, int initialEnergy, IWorldMap map, int [] genome){
        this.map = map;
        this.position = position;
        this.animalEnergy = initialEnergy;
        this.orientation = randomizer.randomOrientation();
        this.genome=genome;
    }


    public AnimalOrientation getOrientation() {
        return this.orientation;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public void changeAnimalOrientation(AnimalOrientation newOrientation){
        this.orientation = newOrientation;
    }

    public void moveForward(){
        Vector2d moveVector = this.orientation.toUnitVector();
        Vector2d newPosition = this.position.add(moveVector);
        // zwierzę może się wszędzie poruszać, tylko niech nie wychodzi poza mapę
        if (map.canMoveTo(newPosition)){
            notifyObservers(this.position, newPosition);
            this.position = newPosition;

        }

    }

    public void useEnergy(int noUsedEnergy){
        this.animalEnergy-=noUsedEnergy;
    }

    public void gainEnergy(int gainedEnergy) {this.animalEnergy+=gainedEnergy;}

    public int getAnimalEnergy(){return this.animalEnergy;}

    public boolean checkIfDead(){
        return animalEnergy<=0;
    }

    public void move(Vector2d vector){
        Vector2d newPosition = this.position.add(vector);
        if (map.canMoveTo(newPosition)){
            notifyObservers(this.position, newPosition);
            this.position = newPosition;
        }
    }


    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void notifyObservers(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }

    public String toString(){
        switch (this.orientation){
            case NORTH: return "N";
            case WEST: return "W";
            case EAST: return "E";
            case SOUTH: return "S";
            case NORTHEAST: return "NE";
            case NORTHWEST: return "NW";
            case SOUTHEAST: return "SE";
            case SOUTHWEST: return "SW";
            default: return "";
        }
    }

    public int [] getGenes(int startIndex, int endIndex){
        return Arrays.copyOfRange(genome, startIndex, endIndex);
    }

    public void checkGenes(){
        // TODO sprawdzić czy wszystkie geny posiada nasze zwierzątko
    }






}

