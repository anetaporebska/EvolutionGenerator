package main;

public class WorldParameters {

    private int initialEnergy ;
    private int initialNoAnimals;
    private int initialNoGrass; // zawsze parzystą poproszę
    private int jungleWidth;
    private int jungleHeight;
    private int worldWidth;
    private int worldHeight;
    private int energyFromGrass;
    private int numberOfDays;


    void setInitialEnergy(int initialEnergy){
        this.initialEnergy = initialEnergy;
    }

    int getInitialEnergy(){
        return initialEnergy;
    }

    void setInitialNoAnimals(int initialNoAnimals){
        this.initialNoAnimals = initialNoAnimals;
    }

    int getInitialNoAnimals(){
        return initialNoAnimals;
    }


    void setInitialNoGrass(int initialNoGrass){
        this.initialNoGrass = initialNoGrass;
    }

    int getInitialNoGrass(){
        return initialNoGrass;
    }


    void setJungleWidth(int jungleWidth){
        this.jungleWidth = jungleWidth;
    }

    int getJungleWidth(){
        return jungleWidth;
    }


    void setJungleHeight(int jungleHeight){
        this.jungleHeight = jungleHeight;
    }

    int getJungleHeight(){
        return jungleHeight;
    }

    void setWorldWidth(int worldWidth){
        this.worldWidth = worldWidth;
    }

    int getWorldWidth(){
        return worldWidth;
    }

    void setWorldHeight(int worldHeight){
        this.worldHeight = worldHeight;
    }

    int getWorldHeight(){
        return worldHeight;
    }


    void setEnergyFromGrass(int energyFromGrass){
        this.energyFromGrass = energyFromGrass;
    }

    int getEnergyFromGrass(){
        return energyFromGrass;
    }

    void setNumberOfDays(int numberOfDays){
        this.numberOfDays = numberOfDays;
    }

    int getNumberOfDays(){
        return numberOfDays;
    }


}
