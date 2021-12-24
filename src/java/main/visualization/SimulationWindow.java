package main.visualization;

import main.interfaces.ISimulationButton;
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


    private StopButton stopButton = new StopButton(this);
    private StartButton startButton = new StartButton(this);
    private FollowButton followButton = new FollowButton(this);
    private AnimalGenomeButton animalGenomeButton = new AnimalGenomeButton(this);
    private DominantGenomeButton dominantGenomeButton = new DominantGenomeButton(this);
    private FollowAnimalButton followAnimalButton = new FollowAnimalButton(this);

    private JTextField numberOfDays = new JTextField();
    private Timer timer = new Timer(50 , this);

    private int n;
    private int day = 1;
    private int displayDay;
    private boolean follow = false;


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

        frame.add(stopButton);
        frame.add(startButton);
        frame.add(followButton);
        frame.add(animalGenomeButton);
        frame.add(dominantGenomeButton);
        frame.add(followAnimalButton);
        frame.add(numberOfDays);

        numberOfDays.setVisible(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==stopButton){
            stopButton.execute();
        }
        else if(actionEvent.getSource()==startButton){
            startButton.execute();
        }
        else if(actionEvent.getSource()==followButton){
            followButton.execute();
        }
        else if (actionEvent.getSource()==followAnimalButton){
            followAnimalButton.execute();
        }
        else if(actionEvent.getSource()==animalGenomeButton){
            animalGenomeButton.execute();
        }
        else if(actionEvent.getSource()==dominantGenomeButton){
            dominantGenomeButton.execute();
        }
        else {
            day+=1;
            if (follow) {
                worldMap.checkIfAlive();
            }
            if (follow && day==displayDay){
                worldMap.removeToFollow();
                follow = false;
                new AnimalStatisticsFrame(statistics);
            }

            if (day==n+1){
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
        int argument = Integer.parseInt(text.trim());
        return argument;

    }


    private class StopButton extends JButton implements ISimulationButton{


        public StopButton(SimulationWindow simulationWindow){
            this.setText("Stop simulation");
            this.setBounds(0,700,300,50);
            this.setFocusable(false);
            this.addActionListener(simulationWindow);
        }


        @Override
        public void execute() {
            timer.stop();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            followButton.setEnabled(true);
            animalGenomeButton.setEnabled(true);
            dominantGenomeButton.setEnabled(true);
        }


    }

    private class StartButton extends JButton implements ISimulationButton{

        public StartButton(SimulationWindow simulationWindow){
            this.setText("Start simulation");
            this.setBounds(0,900, 300, 50);
            this.setFocusable(false);
            this.setEnabled(false);
            this.addActionListener(simulationWindow);
        }

        @Override
        public void execute() {
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            timer.start();
        }

    }

    private class FollowButton extends JButton implements ISimulationButton{

        public FollowButton(SimulationWindow simulationWindow){
            this.setText("Choose animal to follow");
            this.setBounds(0,750, 300, 50);
            this.setFocusable(false);
            this.setEnabled(false);
            this.addActionListener(simulationWindow);
        }

        @Override
        public void execute() {
            followButton.setEnabled(false);
            animalGenomeButton.setEnabled(false);
            dominantGenomeButton.setEnabled(false);
            mapPanel.addMouseListeners(false);

            // get number of days
            numberOfDays.setText("Number of days");
            numberOfDays.setBounds(350, 750, 150, 50);
            numberOfDays.setVisible(true);
            followAnimalButton.setEnabled(true);
            followAnimalButton.setVisible(true);

        }

    }

    private class AnimalGenomeButton extends JButton implements ISimulationButton{

        public AnimalGenomeButton(SimulationWindow simulationWindow){
            this.setText("Choose animal to display genome");
            this.setBounds(0,800, 300, 50);
            this.setFocusable(false);
            this.setEnabled(false);
            this.addActionListener(simulationWindow);
        }

        @Override
        public void execute() {
            followButton.setEnabled(false);
            animalGenomeButton.setEnabled(false);
            dominantGenomeButton.setEnabled(false);
            mapPanel.addMouseListeners(true);

        }

    }

    private class DominantGenomeButton extends JButton implements ISimulationButton{

        public DominantGenomeButton(SimulationWindow simulationWindow){
            this.setText("Animals with dominant genome");
            this.setBounds(0,850, 300, 50);
            this.setFocusable(false);
            this.setEnabled(false);
            this.addActionListener(simulationWindow);
        }

        @Override
        public void execute() {
            followButton.setEnabled(false);
            animalGenomeButton.setEnabled(false);
            dominantGenomeButton.setEnabled(false);
            mapPanel.displayDominant();
            frame.repaint();
        }

    }


    private class FollowAnimalButton extends JButton implements ISimulationButton{

        public FollowAnimalButton(SimulationWindow simulationWindow){
            this.setText("Start following");
            this.setBounds(350,800, 150, 50);
            this.setFocusable(false);
            this.setEnabled(false);
            this.addActionListener(simulationWindow);
            this.setVisible(false);
        }

        @Override
        public void execute() {
            if (mapPanel.checkFollow()){
                try {
                    String N = numberOfDays.getText();
                    displayDay = convert(N) + day;
                    follow = true;
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormatException: " + nfe.getMessage());
                    follow = false;
                }
            }
            followAnimalButton.setEnabled(false);
            followAnimalButton.setVisible(false);
            numberOfDays.setVisible(false);
        }

    }



}