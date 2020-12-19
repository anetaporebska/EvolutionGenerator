package main.visualization;

import main.math.Statistics;

import javax.swing.*;

public class AnimalStatisticsFrame extends JFrame {

    AnimalStatisticsFrame(Statistics statistics){


        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600,350);

        JLabel textLabel = new JLabel("Animal statistics after n days ");
        JLabel childrenNumber = new JLabel( "Number of children after n days: "+statistics.getNumberOfChildren());
        JLabel descendantsNumber = new JLabel( "Number of descendants after n days: "+statistics.getNumberOfDescendants());
        JLabel dayOfDeath = new JLabel("Day of death: "+statistics.getDayOfDeath());

        textLabel.setBounds(0,0,550,50);
        childrenNumber.setBounds(0,100,550,50);
        descendantsNumber.setBounds(0,150, 550, 50);
        dayOfDeath.setBounds(0,200,550,50);

        this.add(textLabel);
        this.add(childrenNumber);
        this.add(descendantsNumber);
        this.add(dayOfDeath);

        this.setVisible(true);
    }


}
