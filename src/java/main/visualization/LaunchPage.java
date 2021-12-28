package main.visualization;

import main.map.WorldParameters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage implements ActionListener {

    private final JFrame frame = new JFrame();

    private final JTextField initialEnergy;
    private final JTextField initialNoAnimals;
    private final JTextField initialNoGrass;
    private final JTextField jungleWidth;
    private final JTextField jungleHeight;
    private final JTextField worldWidth;
    private final JTextField worldHeight;
    private final JTextField energyFromGrass;
    private final JTextField numberOfDays;

    private final JButton endButton = new JButton("Start simulation");


    public LaunchPage() {

        JLabel label1 = new JLabel("Initial energy of animals: ");
        label1.setBounds(10, 0, 400, 30);
        label1.setHorizontalTextPosition(JLabel.CENTER);

        JLabel label2 = new JLabel("Initial number of animals: ");
        label2.setBounds(10, 60, 400, 30);

        JLabel label3 = new JLabel("Initial number of grass: ");
        label3.setBounds(10, 120, 400, 30);

        JLabel label4 = new JLabel("Jungle width: ");
        label4.setBounds(10, 180, 400, 30);

        JLabel label5 = new JLabel("Jungle height: ");
        label5.setBounds(10, 240, 400, 30);

        JLabel label6 = new JLabel("World width: ");
        label6.setBounds(10, 300, 400, 30);

        JLabel label7 = new JLabel("World height: ");
        label7.setBounds(10, 360, 400, 30);

        JLabel label8 = new JLabel("Energy from grass: ");
        label8.setBounds(10, 420, 400, 30);

        JLabel label9 = new JLabel("Number of days: ");
        label9.setBounds(10, 480, 400, 30);

        initialEnergy = new JTextField("200");
        initialEnergy.setBounds(10, 30, 400, 30);

        initialNoAnimals = new JTextField("40");
        initialNoAnimals.setBounds(10, 90, 400, 30);

        initialNoGrass = new JTextField("50");
        initialNoGrass.setBounds(10, 150, 400, 30);

        jungleWidth = new JTextField("10");
        jungleWidth.setBounds(10, 210, 400, 30);

        jungleHeight = new JTextField("10");
        jungleHeight.setBounds(10, 270, 400, 30);

        worldWidth = new JTextField("30");
        worldWidth.setBounds(10, 330, 400, 30);

        worldHeight = new JTextField("30");
        worldHeight.setBounds(10, 390, 400, 30);

        energyFromGrass = new JTextField("50");
        energyFromGrass.setBounds(10, 450, 400, 30);

        numberOfDays = new JTextField("20000");
        numberOfDays.setBounds(10, 510, 400, 30);

        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);
        frame.add(label6);
        frame.add(label7);
        frame.add(label8);
        frame.add(label9);

        frame.add(initialEnergy);
        frame.add(initialNoAnimals);
        frame.add(initialNoGrass);
        frame.add(jungleWidth);
        frame.add(jungleHeight);
        frame.add(worldWidth);
        frame.add(worldHeight);
        frame.add(energyFromGrass);
        frame.add(numberOfDays);
        endButton.setBounds(0, 560, 400, 50);
        endButton.setFocusable(false);
        endButton.addActionListener(this);
        frame.add(endButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 650);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == endButton) {
            try {
                WorldParameters.initialEnergy = convert(initialEnergy.getText());
                WorldParameters.initialNoAnimals = convert(initialNoAnimals.getText());
                WorldParameters.initialNoGrass = convert(initialNoGrass.getText());
                WorldParameters.jungleWidth = convert(jungleWidth.getText());
                WorldParameters.jungleHeight = convert(jungleHeight.getText());
                WorldParameters.worldWidth = convert(worldWidth.getText());
                WorldParameters.worldHeight = convert(worldHeight.getText());
                WorldParameters.energyFromGrass = convert(energyFromGrass.getText());
                WorldParameters.numberOfDays = convert(numberOfDays.getText());

                frame.dispose();
                new Thread(new SimulationWindow()).start();
                new Thread(new SimulationWindow()).start();

            } catch (NumberFormatException nfe) {
                System.out.println("NumberFormatException: " + nfe.getMessage());
            }
        }
    }

    private int convert(String text) {
        return Integer.parseInt(text.trim());
    }
}
