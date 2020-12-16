package main.visualization;

import main.World;
import main.maps.WorldParameters;
import main.interfaces.IEngine;
import main.maps.WorldMap;
import main.math.Statistics;
import main.utilities.SimulationEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationWindow implements ActionListener, Runnable {

    JFrame frame = new JFrame();

    WorldMap worldMap;
    IEngine engine;
    Statistics statistics = new Statistics();
    StatisticsPanel statisticsPanel;
    MapPanel mapPanel;


    JButton stopButton = new JButton("Stop simulation");
    JButton startButton = new JButton("Start simulation");

    JButton followButton = new JButton("Choose animal to follow");
    JButton animalGenomeButton = new JButton("Choose animal to display genome");
    JButton dominantGenomeButton = new JButton("Display animals with dominant genome");


    Timer timer = new Timer(10, this);

    int n;
    int i = 1;

    public void run(){
        timer.start();
    }




    SimulationWindow( WorldParameters worldParameters){

        worldMap = new WorldMap(worldParameters, statistics);
        engine = new SimulationEngine(worldMap,worldParameters);
        worldMap.updateStatistics();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,1000);

        n = worldParameters.getNumberOfDays();

        mapPanel = new MapPanel(worldMap);
        mapPanel.setBounds(0,0,500,500);
        frame.add(mapPanel);

        statisticsPanel = new StatisticsPanel(statistics);
        statisticsPanel.setBounds(0, 500, 500,200);
        frame.add(statisticsPanel);

        stopButton.setBounds(0,700,250,50);
        stopButton.setFocusable(false);
        stopButton.addActionListener(this);


        startButton.setBounds(0,900, 250, 50);
        startButton.setFocusable(false);
        startButton.setEnabled(false);
        startButton.addActionListener(this);


        followButton.setBounds(0,750, 250,50);
        followButton.setFocusable(false);
        followButton.addActionListener(this);
        followButton.setEnabled(false);

        animalGenomeButton.setBounds(0,800,250,50);
        animalGenomeButton.setFocusable(false);
        animalGenomeButton.addActionListener(this);
        animalGenomeButton.setEnabled(false);

        dominantGenomeButton.setBounds(0,850, 250,50);
        dominantGenomeButton.setFocusable(false);
        dominantGenomeButton.addActionListener(this);
        dominantGenomeButton.setEnabled(false);



        frame.add(stopButton);
        frame.add(startButton);
        frame.add(followButton);
        frame.add(animalGenomeButton);
        frame.add(dominantGenomeButton);


        frame.setLayout(null);
        frame.setVisible(true);





    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==stopButton){
            timer.stop();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            followButton.setEnabled(true);
            animalGenomeButton.setEnabled(true);
            dominantGenomeButton.setEnabled(true);

        }
        else if(actionEvent.getSource()==startButton){
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            timer.start();
        }
        else if(actionEvent.getSource()==followButton){
            followButton.setEnabled(false);
            animalGenomeButton.setEnabled(false);
            dominantGenomeButton.setEnabled(false);
            // dodać mouse listener do wszystkich JLabel, gdzie jest zwierzę

            // action listener niech mi zwróci współrzędne zwierzątka

            //



        }
        else if(actionEvent.getSource()==animalGenomeButton){
            followButton.setEnabled(false);
            animalGenomeButton.setEnabled(false);
            dominantGenomeButton.setEnabled(false);
            // dodać mouse listener do wszystkich JLabel, gdzie jest zwierzę
            mapPanel.addMouseListeners();

            // teraz pozwól użytkownikowi kliknąć -> zanim usuniesz!

            // action listener niech mi zwróci współrzędne zwierzątka

            // mapa niech mi zwróci genom tego zwierzątka -> wyświetlić w nowym oknie

            // nie modyfikować nic w tablicy, bo teraz chcę dać start i żeby wszystko było jak przedtem

            // pewnie usunąć mouse listener
            //mapPanel.deleteMouseListeners();



        }
        else if(actionEvent.getSource()==dominantGenomeButton){
            followButton.setEnabled(false);
            animalGenomeButton.setEnabled(false);
            dominantGenomeButton.setEnabled(false);


        }
        else {
            i+=1;
            if (i==n+1){
                timer.stop();
                startButton.setEnabled(false);
                stopButton.setEnabled(false);
            }
            engine.nextDay();
            worldMap.updateStatistics();
            statisticsPanel.updateLabels();
            frame.repaint();
        }



    }
}