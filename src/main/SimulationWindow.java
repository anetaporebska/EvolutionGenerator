package main;

import javax.swing.*;

public class SimulationWindow {

    JFrame frame = new JFrame();
    JLabel label  = new JLabel("Hello!");

    SimulationWindow(){

        label.setBounds(0,0,420,50);

        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);


    }
}
