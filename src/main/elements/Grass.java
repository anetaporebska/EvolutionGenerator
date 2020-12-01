package main.elements;


import main.enums.AnimalOrientation;
import main.interfaces.IPositionChangeObserver;
import main.math.Vector2d;
import main.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.List;

public class Grass {

    private Vector2d position;
    protected IWorldMap map;

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
