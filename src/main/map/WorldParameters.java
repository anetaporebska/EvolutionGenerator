package main.map;

//import org.json.simple.JSONObject;


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
        //JSONParser parser
    }


}
