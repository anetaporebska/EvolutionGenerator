package test;

import main.elements.Genome;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GenomeTest {

    @Test
    public void fixGenomeTest(){
        int [] genome = new int[32];
        for(int i =0; i<32; i+=1){
            genome[i]=0;
        }
        Genome genome0 = new Genome(genome);

        int [] correctGenes = {0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7};
        Genome correctGenome = new Genome(correctGenes);

        correctGenome.fixGenome();
        for(int i: correctGenome.getGenesNumber()){
            assertEquals(true, i>0);
        }

        assertEquals(1, genome0.findMissingGene());

        genome0.fixGenome();

        for(int i: genome0.getGenesNumber()){
            assertEquals(true, i>0);
        }


    }


    @Test
    public void mixGenomesTest(){

        int [] genes1 ={0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7};
        int [] genes2 ={0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7};

        Genome genome1 = new Genome(genes1);
        Genome genome2 = new Genome(genes2);

        Genome resultGenome = Genome.mixGenomes(genome1, genome2);
        assertEquals(32, resultGenome.getGenes().length);
        assertEquals(-1, resultGenome.findMissingGene());


    }




}
