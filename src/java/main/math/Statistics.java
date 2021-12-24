package main.math;

import main.elements.Animal;
import main.elements.Grass;

import java.util.*;

public class Statistics {

    // all animal statistics
    private int numberOfAnimals;
    private int numberOfGrass;
    private int numberOfDeadAnimals =0;
    private int sumOfEnergy =0 ;
    private int sumOfAge = 0;
    private int day=-1;
    private int [] dominantGenome;
    private FamilyTree familyTree = new FamilyTree();
    private int averageNumberOfChildren;

    // single animal statistics
    private int childrenBefore;
    private int descendantsBefore;
    private int startDay;
    private int dayOfDeath;
    private int numberOfChildren;
    private int numberOfDescendants;
    private boolean dead = false;

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

    public int [] getDominantGenome(){
        return dominantGenome;
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
            return 0; //all genes are the same
        }
    }

    public void initializeFamilyTree(Map<Vector2d, ArrayList<Animal>> animals){
        familyTree.addAncestors(animals);
    }

    public void addChild(Animal parent1, Animal parent2, Animal child){
        familyTree.addChild(parent1,parent2,child);
    }

    public void setAverageNumberOfChildren(Map<Vector2d, ArrayList<Animal>> animals){

        int numberOfChildren = 0;
        for (Vector2d vector2d : animals.keySet()){
            for (Animal animal: animals.get(vector2d)){
                numberOfChildren+= familyTree.getNumberOfChildren(animal);
            }
        }
        if(numberOfAnimals==0){
            this.averageNumberOfChildren = 0;
        }
        else{
            this.averageNumberOfChildren = numberOfChildren/numberOfAnimals;
        }

    }

    public int getAverageNumberOfChildren(){
        return averageNumberOfChildren;
    }



    public void addAnimalToFollow(Animal animal){
        childrenBefore = familyTree.getNumberOfChildren(animal);
        descendantsBefore = familyTree.getNumberOfDescendants(animal);
        startDay = getDay();

    }

    public void animalDied(){
        if (!this.dead){
            this.dead = true;
            this.dayOfDeath = getDay();
        }
    }

    public int getDayOfDeath(){
        if (dayOfDeath==0){
            return -1;
        }
        return dayOfDeath;
    }

    public void stopFollowing(Animal animal){
        numberOfChildren = familyTree.getNumberOfChildren(animal) - childrenBefore;
        numberOfDescendants = familyTree.getNumberOfDescendants(animal)-descendantsBefore;
    }

    public int getNumberOfDescendants(){
        return numberOfDescendants;
    }

    public int getNumberOfChildren(){
        return numberOfChildren;
    }





}
