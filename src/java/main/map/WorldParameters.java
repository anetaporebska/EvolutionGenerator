package main.map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class WorldParameters {

    private int initialEnergy ;
    private int initialNoAnimals;
    private int initialNoGrass;
    private int jungleWidth;
    private int jungleHeight;
    private int worldWidth;
    private int worldHeight;
    private int energyFromGrass;
    private int numberOfDays;

    public void setInitialEnergy(int initialEnergy){
        this.initialEnergy = initialEnergy;
    }

    public int getInitialEnergy(){
        return initialEnergy;
    }

    public void setInitialNoAnimals(int initialNoAnimals){
        this.initialNoAnimals = initialNoAnimals;
    }

    public int getInitialNoAnimals(){
        return initialNoAnimals;
    }

    public void setInitialNoGrass(int initialNoGrass){
        this.initialNoGrass = initialNoGrass;
    }

    public int getInitialNoGrass(){
        return initialNoGrass;
    }

    public void setJungleWidth(int jungleWidth){
        this.jungleWidth = jungleWidth;
    }

    public int getJungleWidth(){
        return jungleWidth;
    }

    public void setJungleHeight(int jungleHeight){
        this.jungleHeight = jungleHeight;
    }

    public int getJungleHeight(){
        return jungleHeight;
    }

    public void setWorldWidth(int worldWidth){
        this.worldWidth = worldWidth;
    }

    public int getWorldWidth(){
        return worldWidth;
    }

    public void setWorldHeight(int worldHeight){
        this.worldHeight = worldHeight;
    }

    public int getWorldHeight(){
        return worldHeight;
    }

    public void setEnergyFromGrass(int energyFromGrass){
        this.energyFromGrass = energyFromGrass;
    }

    public int getEnergyFromGrass(){
        return energyFromGrass;
    }

    public void setNumberOfDays(int numberOfDays){
        this.numberOfDays = numberOfDays;
    }

    public int getNumberOfDays(){
        return numberOfDays;
    }


    public void readParameters(){
        JSONParser parser = new JSONParser();

        try {
            Object object = parser.parse(new FileReader("parameters.json"));
            JSONObject jsonObject = (JSONObject) object;
            int numberOfAnimals = Integer.parseInt(jsonObject.get("number of animals").toString());
            int startEnergy = Integer.parseInt(jsonObject.get("start energy").toString());
            int numberOfGrass = Integer.parseInt(jsonObject.get("number of grass").toString());
            int jungleWidth = Integer.parseInt(jsonObject.get("jungle width").toString());
            int jungleHeight = Integer.parseInt(jsonObject.get("jungle height").toString());
            int worldWidth = Integer.parseInt(jsonObject.get("world width").toString());
            int worldHeight = Integer.parseInt(jsonObject.get("world height").toString());
            int days = Integer.parseInt(jsonObject.get( "number of days").toString());
            int grassEnergy= Integer.parseInt(jsonObject.get( "energy of grass").toString());

            setInitialNoAnimals(numberOfAnimals);
            setInitialEnergy(startEnergy);
            setInitialNoGrass(numberOfGrass);
            setJungleWidth(jungleWidth);
            setJungleHeight(jungleHeight);
            setWorldWidth(worldWidth);
            setWorldHeight(worldHeight);
            setNumberOfDays(days);
            setEnergyFromGrass(grassEnergy);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }


}
