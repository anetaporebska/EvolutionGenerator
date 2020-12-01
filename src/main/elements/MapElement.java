package main.elements;

import main.math.Vector2d;
import main.interfaces.IMapElement;
import main.interfaces.IWorldMap;

public class MapElement implements IMapElement {

    protected IWorldMap map;
    protected Vector2d position;

    public MapElement(IWorldMap map, Vector2d position){
        this.map = map;
        this.position = position;
    }

    public Vector2d getPosition(){return this.position;}

    public void setPosition(Vector2d newPosition){
        this.position = newPosition;
    }


}
