package main;

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

    WorldMap worldMap;
    IEngine engine;

    // jeśli chcę wybrać dane zwierzę to może MouseListener -> > > > BroCode

    SimulationWindow( WorldParameters worldParameters){

        worldMap = new WorldMap(worldParameters);
        engine = new SimulationEngine(worldMap,worldParameters);

        //label.setBounds(0,0,420,50);

        //frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);


        MapPanel mapPanel = new MapPanel(worldMap);
        mapPanel.setBounds(0,0,420,420);
        frame.add(mapPanel);


        frame.setLayout(null);
        frame.setVisible(true);

        int n = worldParameters.getNumberOfDays();
        // jakiś timer
        Timer timer = new Timer(1000, this);

        for (int i=0; i<n; i++){
            // wyświetlenie aktualnej mapPanel
            timer.start();
        }
        //timer.stop();




        // nowy dzień -> nowy wygląd wszystkiego, ustawić jakiś timer i zaaktualizować MapPanel (mogę na przykład podać pozycję,
        // na której się coś zmieniło i zmienić zawartość MapPanel dla tego elementu)


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        engine.nextDay();
        frame.repaint();
    }
}
