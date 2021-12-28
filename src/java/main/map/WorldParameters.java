package main.map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class WorldParameters {

    public static int initialEnergy;
    public static int initialNoAnimals;
    public static int initialNoGrass;
    public static int jungleWidth;
    public static int jungleHeight;
    public static int worldWidth;
    public static int worldHeight;
    public static int energyFromGrass;
    public static int numberOfDays;

    public static void readParameters() {
        JSONParser parser = new JSONParser();

        try {
            Object object = parser.parse(new FileReader("parameters.json"));
            JSONObject jsonObject = (JSONObject) object;
            initialNoAnimals = Integer.parseInt(jsonObject.get("number of animals").toString());
            initialEnergy = Integer.parseInt(jsonObject.get("start energy").toString());
            initialNoGrass = Integer.parseInt(jsonObject.get("number of grass").toString());
            jungleWidth = Integer.parseInt(jsonObject.get("jungle width").toString());
            jungleHeight = Integer.parseInt(jsonObject.get("jungle height").toString());
            worldWidth = Integer.parseInt(jsonObject.get("world width").toString());
            worldHeight = Integer.parseInt(jsonObject.get("world height").toString());
            numberOfDays = Integer.parseInt(jsonObject.get("number of days").toString());
            energyFromGrass = Integer.parseInt(jsonObject.get("energy of grass").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
