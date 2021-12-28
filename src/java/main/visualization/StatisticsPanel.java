package main.visualization;

import main.math.Statistics;
import main.utilities.AverageStatistics;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class StatisticsPanel extends JPanel {

    private final JLabel dayNumber = new JLabel();
    private final JLabel numberOfAnimals = new JLabel();
    private final JLabel numberOfGrass = new JLabel();
    private final JLabel dominantGenome = new JLabel();
    private final JLabel averageEnergy = new JLabel();
    private final JLabel averageAge = new JLabel();
    private final JLabel averageNumberOfChildren = new JLabel();

    private final Statistics statistics;
    private final AverageStatistics averageStatistics = new AverageStatistics();

    StatisticsPanel(Statistics statistics) {
        this.statistics = statistics;
        this.setLayout(new GridLayout(7, 1, 0, 0));

        updateLabels();
        this.add(dayNumber);
        this.add(numberOfAnimals);
        this.add(numberOfGrass);
        this.add(dominantGenome);
        this.add(averageEnergy);
        this.add(averageAge);
        this.add(averageNumberOfChildren);

        this.setVisible(true);
    }

    public void updateLabels() {
        int result;

        result = statistics.getDay();
        dayNumber.setText("Day: " + result);
        averageStatistics.addDay(result);

        result = statistics.getNumberOfAnimals();

        numberOfAnimals.setText("Current number of animals: " + result);
        averageStatistics.addNumberOfAnimals(result);

        result = statistics.getNumberOfGrass();
        numberOfGrass.setText("Current number of grass: " + result);
        averageStatistics.addNumberOfGrass(result);

        int[] resultArray = statistics.getDominantGenome();
        dominantGenome.setText("Dominant genome: " + Arrays.toString(resultArray));
        averageStatistics.addGenome(resultArray);

        result = statistics.getAverageEnergy();
        averageEnergy.setText("Average energy for living animals: " + result);
        averageStatistics.addAverageEnergy(result);

        result = statistics.getAverageAge();
        averageAge.setText("Average age for dead animals: " + statistics.getAverageAge());
        averageStatistics.addAverageAge(result);

        result = statistics.getAverageNumberOfChildren();
        averageNumberOfChildren.setText("Average number of children: " + statistics.getAverageNumberOfChildren());
        averageStatistics.addAverageNumberOfChildren(result);
    }

    public void getAverageStatistic() {
        averageStatistics.write();
    }
}
