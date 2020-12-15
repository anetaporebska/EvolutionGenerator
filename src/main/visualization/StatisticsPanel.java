package main.visualization;

import main.math.Statistics;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {

    JLabel dayNumber = new JLabel();
    JLabel numberOfAnimals = new JLabel();
    JLabel numberOfGrass = new JLabel();
    JLabel dominantGenome = new JLabel();
    JLabel averageEnergy = new JLabel();
    JLabel averageAge = new JLabel();
    JLabel averageNumberOfChildren = new JLabel();



    Statistics statistics;


    StatisticsPanel(Statistics statistics){
        this.statistics = statistics;
        this.setLayout(new GridLayout(7,1));

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

    public void updateLabels(){
        dayNumber.setText("Day: " + statistics.getDay());
        numberOfAnimals.setText("Current number of animals: "+statistics.getNumberOfAnimals());
        numberOfGrass.setText("Current number of grass: "+statistics.getNumberOfGrass());
        dominantGenome.setText("Dominant genome: ");
        averageEnergy.setText("Average energy for living animals: "+statistics.getAverageEnergy());
        averageAge.setText("Average age for dead animals: "+statistics.getAverageAge());
        averageNumberOfChildren.setText("Average number of children: ");


    }




}

/*
dayNumber = new JLabel("Day: " + statistics.getDay());
        numberOfAnimals = new JLabel("Current number of animals: "+statistics.getNumberOfAnimals());
        numberOfGrass = new JLabel("Current number of grass: "+statistics.getNumberOfGrass());
        dominantGenome = new JLabel("Dominant genome: ");
        averageEnergy = new JLabel("Average energy for living animals: "+statistics.getAverageEnergy());
        averageAge = new JLabel("Average age for dead animals: "+statistics.getAverageAge());
        averageNumberOfChildren = new JLabel("Average number of children: ");

 */