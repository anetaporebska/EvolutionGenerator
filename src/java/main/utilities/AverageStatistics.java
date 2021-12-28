package main.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AverageStatistics {

    private final ArrayList<Integer> days = new ArrayList<>();
    private final ArrayList<Integer> numberOfAnimals = new ArrayList<>();
    private final ArrayList<Integer> numberOfGrass = new ArrayList<>();
    private final ArrayList<Integer> averageEnergy = new ArrayList<>();
    private final ArrayList<Integer> averageAge = new ArrayList<>();
    private final ArrayList<Integer> averageNumberOfChildren = new ArrayList<>();
    private final ArrayList<int[]> dominantGenome = new ArrayList<>();
    private final HashMap<int[], Integer> genomes = new HashMap<>();

    public void addDay(int day) {
        days.add(day);
    }

    public void addNumberOfAnimals(int number) {
        numberOfAnimals.add(number);
    }

    public void addNumberOfGrass(int number) {
        numberOfGrass.add(number);
    }

    public void addAverageEnergy(int energy) {
        averageEnergy.add(energy);
    }

    public void addAverageAge(int age) {
        averageAge.add(age);
    }

    public void addAverageNumberOfChildren(int children) {
        averageNumberOfChildren.add(children);
    }

    public void addGenome(int[] genome) {
        dominantGenome.add(genome);
    }

    public void write() {
        try {
            FileWriter fileWriter = new FileWriter("statistics.txt");
            fileWriter.write("Day \t Average number of animals \t Average number of grass \t Average energy \t Average age \t Average number of children \t Average genome \n");
            for (int i = 0; i < days.size(); i++) {
                int day = days.get(i);
                int animals = getAverage(i + 1, numberOfAnimals);
                int grass = getAverage(i + 1, numberOfGrass);
                int energy = getAverage(i + 1, averageEnergy);
                int age = getAverage(i + 1, averageAge);
                int children = getAverage(i + 1, averageNumberOfChildren);
                int[] genome = getAverageGenome(i);

                fileWriter.write(day + "\t" + animals + "\t" + grass + "\t" + energy + "\t" + age + "\t" + children + "\t" + Arrays.toString(genome) + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println("An error occurred " + exception.getMessage());
        }
    }

    private int getAverage(int end, ArrayList<Integer> data) {
        int sum = 0;
        for (int i = 0; i < end; i++) {
            sum += data.get(i);
        }
        return sum / end;
    }

    private int[] getAverageGenome(int end) {
        int[] key = dominantGenome.get(end);
        if (genomes.containsKey(key)) {
            int i = genomes.get(key);
            genomes.put(key, i + 1);
        } else {
            genomes.put(key, 1);
        }
        int maxNumber = 0;
        int[] result = null;
        for (int[] keys : genomes.keySet()) {
            if (genomes.get(keys) > maxNumber) {
                result = keys;
                maxNumber = genomes.get(keys);
            }
        }
        return result;
    }
}
