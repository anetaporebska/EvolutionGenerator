package main.elements;

import main.math.Randomizer;

import java.util.Arrays;

public class Genome {

    private final int numberOfGenes = 32;


    // TODO genes - sorted array?
    private int [] genes;
    private int [] genesNumber;


    private final int [] initialGenes = {0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7};
    private final int [] initialGenesNumber = {4,4,4,4,4,4,4,4};


    public Genome(){
        this.genes = initialGenes;
        this.genesNumber = initialGenesNumber;
    }

    public Genome(int [] genes){
        this.genes = genes;
        this.genesNumber = countGenes();

    }

    public int [] getGenes(){
        return this.genes;
    }

    public int [] getGenesNumber(){
        return genesNumber;
    }

    static Randomizer randomizer = new Randomizer();

    private int [] countGenes(){
        int [] count = new int[8];
        for(int i =0; i<32; i++){
            count[genes[i]]+=1;
        }
        return count;
    }

    public static Genome mixGenomes(Genome genome1, Genome genome2){



        int idx1 = randomizer.randomIndex();
        int idx2 = randomizer.randomIndex();
        int a = randomizer.randomParent();
        int [] array1;
        int [] array2;
        int [] array3;

        int [] genes1 = genome1.getGenes();
        int [] genes2 = genome2.getGenes();

        if(idx1>idx2){
            int p = idx1;
            idx1 = idx2;
            idx2 = p;
        }
        int i =0;
        if (a == 0 ){ // biorę pierwszą część genomu od animal1
            array1 = Arrays.copyOfRange(genes1, 0, idx1);
            i+=1;
        }
        else {
            array1 = Arrays.copyOfRange(genes2, 0, idx1);
        }

        a = randomizer.randomParent();
        if (a == 0 ){
            array2 = Arrays.copyOfRange(genes1, idx1, idx2);
            i+=1;
        }
        else {
            array2 = Arrays.copyOfRange(genes2, idx1, idx2);
        }

        if (i==0){
            array3 = Arrays.copyOfRange(genes1, idx2, 32);
        }
        else if (i==2){
            array3 = Arrays.copyOfRange(genes2, idx2, 32);
        }
        else{
            a = randomizer.randomParent();
            if (a == 0 ){
                array3 = Arrays.copyOfRange(genes1, idx2, 32);
            }
            else {
                array3 = Arrays.copyOfRange(genes2, idx2, 32);
            }
        }


        int len1 = array1.length;
        int len2 = array2.length;
        int len3 = array3.length;

        int [] result = new int[len1+len2+len3];
        System.arraycopy(array1,0,result,0,len1);
        System.arraycopy(array2, 0, result, len1, len2);
        System.arraycopy(array3, 0, result, len2, len3);

        Genome genome = new Genome(result);
        genome.fixGenome();


        return genome;


    }


    public void displayGenome(){
        Arrays.toString(this.genes);
    }


    // jeśli brakuje jakiegoś genu
    public void fixGenome(){

        Arrays.sort(this.genes);

        int missing = findMissingGene();
        while (missing!=-1){
            int idx = randomizer.randomIndex();
            while(genesNumber[genes[idx]]<2){
                idx = randomizer.randomIndex();
            }
            genesNumber[genes[idx]]-=1;
            genes[idx] = missing;
            genesNumber[missing]+=1;
            missing = findMissingGene();

        }
        Arrays.sort(this.genes);


    }

    public int findMissingGene(){

        for (int i=0; i<8; i++){
            if(genesNumber[i]==0){
                return i;
            }
        }

        return -1;

    }


}
