package test;

import main.math.Statistics;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticsTest {

    @Test
    public void dominantGenomeTest(){
        int [] genome1 = {0,0,0,0,0,0,0,32};
        int [] genome2 = {1,1,1,1,1,1,1,25};
        int [] genome3 = {2,2,2,10,10,2,2,2};
        int [] genome4 = {4,4,4,4,4,4,4,4};
        int [] genome5 = {2,2,2,10,10,2,2,2};
        int [] genome6 = {0,0,6,6,6,6,6,2};

        Statistics statistics = new Statistics();

        ArrayList<int[]> animalGenomes = new ArrayList<>();
        animalGenomes.add(genome1);
        animalGenomes.add(genome2);
        animalGenomes.add(genome3);
        animalGenomes.add(genome4);
        animalGenomes.add(genome5);
        animalGenomes.add(genome6);

        System.out.println(Arrays.toString( statistics.findDominantGenome(animalGenomes)));
        assertArrayEquals(statistics.findDominantGenome(animalGenomes), genome3);
    }
}
