package main.visualization;

import main.math.Statistics;

import javax.swing.*;
import java.awt.*;

public class AnimalStatisticsFrame extends JFrame {

    public AnimalStatisticsFrame(Statistics statistics) {

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 150);
        this.setLayout(new GridLayout(4, 1));

        JLabel textLabel = new JLabel("Animal statistics after n days: ");
        JLabel childrenNumber = new JLabel("Number of children after n days: " + statistics.getNumberOfChildren());
        JLabel descendantsNumber = new JLabel("Number of descendants after n days: " + statistics.getNumberOfDescendants());
        JLabel dayOfDeath = new JLabel("Day of death: " + statistics.getDayOfDeath());

        textLabel.setBounds(0, 0, 550, 20);
        childrenNumber.setBounds(0, 20, 550, 20);
        descendantsNumber.setBounds(0, 40, 550, 20);
        dayOfDeath.setBounds(0, 4, 550, 20);

        this.add(textLabel);
        this.add(childrenNumber);
        this.add(descendantsNumber);
        this.add(dayOfDeath);
        this.setVisible(true);
    }
}
