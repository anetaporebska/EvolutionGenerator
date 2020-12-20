package main.elements;

import main.math.Vector2d;
import main.interfaces.IWorldMap;

public class Grass {

    private Vector2d position;
    private IWorldMap map;

    public Grass(IWorldMap map, Vector2d position){
        this.map = map;
        this.position = position;
    }

    public String toString(){
        return "*";
    }

    public Vector2d getPosition(){return this.position;}

    public void setPosition(Vector2d newPosition){
        this.position = newPosition;
    }

}
