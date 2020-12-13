package main.visualization;

import main.World;
import main.maps.WorldParameters;
import main.interfaces.IEngine;
import main.maps.WorldMap;
import main.utilities.SimulationEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationWindow implements ActionListener {

    JFrame frame = new JFrame();
    //JLabel label  = new JLabel("Hello!");

    // tu będę potrzebować mapę wizualizacji -> animacje ; JPanel()
    // może GridLayout ??

    WorldMap worldMap1;
    IEngine engine1;

    WorldMap worldMap2;
    IEngine engine2;

    // jeśli chcę wybrać dane zwierzę to może MouseListener -> > > > BroCode

    SimulationWindow( WorldParameters worldParameters){

        worldMap1 = new WorldMap(worldParameters);
        engine1 = new SimulationEngine(worldMap1,worldParameters);

        worldMap2 = new WorldMap(worldParameters);
        engine2 = new SimulationEngine(worldMap2,worldParameters);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,500);


        MapPanel mapPanel1 = new MapPanel(worldMap1);
        mapPanel1.setBounds(0,0,420,420);
        frame.add(mapPanel1);

        MapPanel mapPanel2 = new MapPanel(worldMap2);
        mapPanel2.setBounds(450,0,420,420);
        frame.add(mapPanel2);


        frame.setLayout(null);
        frame.setVisible(true);

        int n = worldParameters.getNumberOfDays();

        Timer timer = new Timer(1000, this);

        for (int i=0; i<n; i++){
            timer.start();
        }




        // nowy dzień -> nowy wygląd wszystkiego, ustawić jakiś timer i zaaktualizować MapPanel (mogę na przykład podać pozycję,
        // na której się coś zmieniło i zmienić zawartość MapPanel dla tego elementu)


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        engine1.nextDay();
        engine2.nextDay();
        frame.repaint();
    }
}
