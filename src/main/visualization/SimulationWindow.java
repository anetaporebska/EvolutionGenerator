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

public class SimulationWindow implements ActionListener {

    JFrame frame = new JFrame();

    WorldMap worldMap1;
    IEngine engine1;

    WorldMap worldMap2;
    IEngine engine2;

    Statistics statistics1 = new Statistics();
    Statistics statistics2 = new Statistics();

    StatisticsPanel statisticsPanel1;
    StatisticsPanel statisticsPanel2;


    // jeśli chcę wybrać dane zwierzę to może MouseListener -> > > > BroCode

    int n;
    int i =1;

    SimulationWindow( WorldParameters worldParameters){

        worldMap1 = new WorldMap(worldParameters, statistics1);
        engine1 = new SimulationEngine(worldMap1,worldParameters);
        worldMap1.updateStatistics();

        worldMap2 = new WorldMap(worldParameters, statistics2);
        engine2 = new SimulationEngine(worldMap2,worldParameters);
        worldMap2.updateStatistics();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);

        n = worldParameters.getNumberOfDays();

        MapPanel mapPanel1 = new MapPanel(worldMap1);
        mapPanel1.setBounds(0,0,420,420);
        frame.add(mapPanel1);

        MapPanel mapPanel2 = new MapPanel(worldMap2);
        mapPanel2.setBounds(450,0,420,420);
        frame.add(mapPanel2);

        statisticsPanel1 = new StatisticsPanel(statistics1);
        statisticsPanel1.setBounds(0, 500, 420,420);
        frame.add(statisticsPanel1);

        statisticsPanel2 = new StatisticsPanel(statistics2);
        statisticsPanel2.setBounds(450, 500, 420, 420);
        frame.add(statisticsPanel2);


        frame.setLayout(null);
        frame.setVisible(true);



        Timer timer = new Timer(10, this);

        timer.start();


        // nowy dzień -> nowy wygląd wszystkiego, ustawić jakiś timer i zaaktualizować MapPanel (mogę na przykład podać pozycję,
        // na której się coś zmieniło i zmienić zawartość MapPanel dla tego elementu)


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println(i);
        i+=1;
        if (i==n+1){
            Timer s = (Timer) actionEvent.getSource();
            s.stop();
        }
        System.out.println("engine1");
        engine1.nextDay(); // infinity!
        System.out.println("engine2");
        engine2.nextDay();
        worldMap1.updateStatistics();
        worldMap2.updateStatistics();
        statisticsPanel1.updateLabels();
        statisticsPanel2.updateLabels();



        frame.repaint();

    }
}
