package main.interfaces;

import main.elements.Animal;
import main.math.Vector2d;

public interface IPositionChangeObserver {

    void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal);


}
