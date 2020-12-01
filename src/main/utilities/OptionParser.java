package main.utilities;

import main.enums.MoveDirection;

import java.util.Arrays;

public class OptionParser {
    public MoveDirection[] parse(String [] args){
        MoveDirection [] direction = new MoveDirection[args.length];

        int i = 0;

        for (String d : args){
            switch (d){
                case "f": case "forward":
                    direction [i] = MoveDirection.FORWARD;
                    i+=1;
                    break;
                case "b": case "backward":
                    direction [i] = MoveDirection.BACKWARD;
                    i+=1;
                    break;
                case "l": case "left":
                    direction [i] = MoveDirection.LEFT;
                    i+=1;
                    break;
                case "r": case "right":
                    direction [i] = MoveDirection.RIGHT;
                    i+=1;
                    break;
                default:
                    throw new IllegalArgumentException(d + " is not legal move specification");
            }
        }
        return Arrays.copyOfRange(direction,0,i);
    }
}