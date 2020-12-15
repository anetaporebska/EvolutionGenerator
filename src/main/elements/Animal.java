package main.elements;

import main.math.Randomizer;
import main.enums.AnimalOrientation;
import main.math.Vector2d;
import main.interfaces.IPositionChangeObserver;
import main.interfaces.IWorldMap;
import java.util.ArrayList;
import java.util.List;


public class Animal{
    private AnimalOrientation orientation;
    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private Vector2d position;
    private IWorldMap map;

    private Genome genome;
    private int animalEnergy;

    // TODO age of animal
    private int age = 0;

    private Randomizer randomizer = new Randomizer();

    public Animal(Vector2d position, int initialEnergy, IWorldMap map){
        this.map = map;
        this.position = position;
        this.animalEnergy = initialEnergy;
        this.orientation = randomizer.randomOrientation();
        this.genome = new Genome();
    }


    public Animal(Vector2d position, int initialEnergy, IWorldMap map, Genome genome){
        this.map = map;
        this.position = position;
        this.animalEnergy = initialEnergy;
        this.orientation = randomizer.randomOrientation();
        this.genome= genome;
    }


    public AnimalOrientation getOrientation() {
        return this.orientation;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public Genome getGenome(){ return this.genome;}

    public int getAge(){
        return this.age;
    }

    public void changeAge(){
        this.age+=1;
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




}

