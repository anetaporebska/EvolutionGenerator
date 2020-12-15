package main.math;

import main.elements.Animal;
import main.elements.Grass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TODO Statistics jako 3 observer dla mapy? - tylko to już nie będzie positionChanged
public class Statistics {

    private int numberOfAnimals;
    private int numberOfGrass;
    //private float averageEnergy = 0;
    //private float averageAge =0;
    private int numberOfDeadAnimals =0;
    private int sumOfEnergy =0 ;
    private int sumOfAge = 0;
    private int day=-1;

    // jak wyszukiwać dominujący genotyp? - tablice z genami w porządku leksykograficznym?

    //TODO dominujące genotypy

    // TODO średnia liczba dzieci dla żyjących zwierząt - drzewo jakieś trzeba ogarnąć

    // TODO statystyki dla wybranego zwirzęcia: liczba wszystkich dzieci po n epokach, liczba wszystkich potomków, epoka w której zmarło

    public int getNumberOfAnimals(){
        return this.numberOfAnimals;
    }
    public int getNumberOfGrass(){
        return this.numberOfGrass;
    }

    public int getAverageEnergy(){
        if (this.numberOfAnimals!=0){
            return this.sumOfEnergy/this.numberOfAnimals;
        }
        else {
            return 0;
        }

    }

    public int getAverageAge(){
        if (this.numberOfDeadAnimals!=0){
            return this.sumOfAge/this.numberOfDeadAnimals;
        }
        else {
            return 0;
        }

    }

    public int getDay(){
        return this.day;
    }

    public void updateStatistics(Map<Vector2d, ArrayList<Animal>> animals, Map<Vector2d, Grass> grass){
        this.numberOfAnimals=0;
        this.sumOfEnergy=0;
        this.day+=1;
        for (Vector2d position : animals.keySet()){

            for (Animal animal : animals.get(position)){

                if (animal.getAnimalEnergy()>0){
                    this.numberOfAnimals+=1;
                    this.sumOfEnergy+=animal.getAnimalEnergy();
                }
                else{
                    this.numberOfDeadAnimals+=1;
                    this.sumOfAge+=animal.getAge();
                }

            }
        }

        this.numberOfGrass = grass.keySet().size();


    }





}
