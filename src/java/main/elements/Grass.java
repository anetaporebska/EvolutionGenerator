package main.elements;

import main.interfaces.IWorldMap;
import main.math.Vector2d;

public class Grass {

    private Vector2d position;

    public Grass(IWorldMap map, Vector2d position) {
        this.position = position;
    }

    public String toString() {
        return "*";
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public void setPosition(Vector2d newPosition) {
        this.position = newPosition;
    }
}
