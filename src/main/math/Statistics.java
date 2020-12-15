package main.math;

import main.elements.Animal;
import main.elements.Grass;

import java.util.*;

// TODO Statistics jako 3 observer dla mapy? - tylko to już nie będzie positionChanged
public class Statistics {

    private int numberOfAnimals;
    private int numberOfGrass;
    private int numberOfDeadAnimals =0;
    private int sumOfEnergy =0 ;
    private int sumOfAge = 0;
    private int day=-1;
    private int [] dominantGenome;

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

    public String getDominantGenome(){
        return Arrays.toString(dominantGenome);
    }

    public void updateStatistics(Map<Vector2d, ArrayList<Animal>> animals, Map<Vector2d, Grass> grass){
        this.numberOfAnimals=0;
        this.sumOfEnergy=0;
        this.day+=1;
        ArrayList<int[]> animalGenes = new ArrayList<>();
        for (Vector2d position : animals.keySet()){

            for (Animal animal : animals.get(position)){

                if (animal.getAnimalEnergy()>0){
                    this.numberOfAnimals+=1;
                    this.sumOfEnergy+=animal.getAnimalEnergy();
                    animalGenes.add(animal.getGenome().getGenesNumber());
                }
                else{
                    this.numberOfDeadAnimals+=1;
                    this.sumOfAge+=animal.getAge();
                }

            }
        }

        this.numberOfGrass = grass.keySet().size();

        this.dominantGenome = findDominantGenome(animalGenes);


    }

    // muszę je jakoś posortować?
    public int [] findDominantGenome(ArrayList<int[]> animalGenes){

        if (animalGenes.size() ==0){
            return null;
        }

        Collections.sort(animalGenes,Statistics.Comparator::compareArrays);

        int length =1;
        int [] genome = animalGenes.get(0);
        int maxLength =0;

        for (int i=0; i<animalGenes.size()-1; i++){
            if( Arrays.equals(animalGenes.get(i), animalGenes.get(i+1))) {
                length+=1;
                if (length>maxLength){
                    maxLength = length;
                    genome = animalGenes.get(i);
                }

            }
            else{
                length=1;
            }
        }

        return genome;




    }


    private static class Comparator {

        public static int compareArrays(int[] array1, int[] array2){

            for (int i=0; i<8; i++){
                if (array1[i]<array2[i]){
                    return -1;
                }
                else if(array1[i] > array2[i]){
                    return 1;
                }
            }
            return 0; //wszystkie geny są takie same

        }

    }





}
