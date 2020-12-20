package main.visualization;


import main.map.WorldParameters;
import main.interfaces.IEngine;
import main.map.WorldMap;
import main.math.Statistics;
import main.utilities.SimulationEngine;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationWindow implements ActionListener, Runnable {

    private JFrame frame = new JFrame();

    private WorldMap worldMap;
    private IEngine engine;
    private Statistics statistics = new Statistics();
    private StatisticsPanel statisticsPanel;
    private MapPanel mapPanel;


    private JButton stopButton = new JButton("Stop simulation");
    private JButton startButton = new JButton("Start simulation");

    private JButton followButton = new JButton("Choose animal to follow");
    private JButton animalGenomeButton = new JButton("Choose animal to display genome");
    private JButton dominantGenomeButton = new JButton("Animals with dominant genome");

    private JButton followAnimalButton = new JButton("Start following");

    private JTextField numberOfDays;


    private Timer timer = new Timer(50 , this);

    int n;
    int i = 1;
    int displayDay;


    public void run(){
        timer.start();
    }

    public SimulationWindow( WorldParameters worldParameters){

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

        stopButton.setBounds(0,700,300,50);
        stopButton.setFocusable(false);
        stopButton.addActionListener(this);


        startButton.setBounds(0,900, 300, 50);
        startButton.setFocusable(false);
        startButton.setEnabled(false);
        startButton.addActionListener(this);


        followButton.setBounds(0,750, 300,50);
        followButton.setFocusable(false);
        followButton.addActionListener(this);
        followButton.setEnabled(false);

        animalGenomeButton.setBounds(0,800,300,50);
        animalGenomeButton.setFocusable(false);
        animalGenomeButton.addActionListener(this);
        animalGenomeButton.setEnabled(false);

        dominantGenomeButton.setBounds(0,850, 300,50);
        dominantGenomeButton.setFocusable(false);
        dominantGenomeButton.addActionListener(this);
        dominantGenomeButton.setEnabled(false);

        followAnimalButton.setBounds(350, 800, 150,50);
        followAnimalButton.setFocusable(true);
        followAnimalButton.setEnabled(false);
        followAnimalButton.addActionListener(this);



        frame.add(stopButton);
        frame.add(startButton);
        frame.add(followButton);
        frame.add(animalGenomeButton);
        frame.add(dominantGenomeButton);
        frame.add(followAnimalButton);


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
            mapPanel.addMouseListeners(false);

            // get number of days
            numberOfDays = new JTextField("Number of days");
            numberOfDays.setBounds(350, 750, 150,50);
            frame.add(numberOfDays);
            followAnimalButton.setEnabled(true);
        }
        else if (actionEvent.getSource()==followAnimalButton){
            //TODO wyjątek
            String N = numberOfDays.getText();
            displayDay = convert(N) + i;
            followAnimalButton.setEnabled(false);
        }


        else if(actionEvent.getSource()==animalGenomeButton){
            followButton.setEnabled(false);
            animalGenomeButton.setEnabled(false);
            dominantGenomeButton.setEnabled(false);
            mapPanel.addMouseListeners(true);


        }
        else if(actionEvent.getSource()==dominantGenomeButton){
            followButton.setEnabled(false);
            animalGenomeButton.setEnabled(false);
            dominantGenomeButton.setEnabled(false);
            mapPanel.displayDominant();
            frame.repaint();

        }
        else {
            i+=1;
            worldMap.checkIfAlive();

            if (i==displayDay){
                worldMap.removeToFollow();
                new AnimalStatisticsFrame(statistics);
            }

            if (i==n+1){
                timer.stop();
                startButton.setEnabled(false);
                stopButton.setEnabled(false);
                statisticsPanel.getAverageStatistic();
            }
            engine.nextDay();
            worldMap.updateStatistics();
            statisticsPanel.updateLabels();
            frame.repaint();
        }



    }

    private int convert(String text){
        try{
            int argument = Integer.parseInt(text.trim());
            return argument;
        }
        catch (NumberFormatException nfe){
            System.out.println("NumberFormatException: "+nfe.getMessage());
        }
        return -1; //TODO to nie do końca może tak być
    }


}