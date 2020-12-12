package main;

// wyświetlanie aktualnej mapy ?


// za każdym razem gdy cokolwiek się zmieni musimy informować o tym mapPanel
// energia zwierzęcia, nowe zwierzę, nowa trawa, trawa zjedzona wszystko !!!

// nieoptymalnie można tworzyć za każdym razem nowy MapPanel

import main.maps.WorldMap;
import main.math.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapPanel extends JPanel {

    int worldWidth;
    int worldHeight;

    WorldMap worldMap;


    // potrzebuję rozmiar każdej komórki jeszcze np 2 piksele

    // może 2d array of labels -> może być w sumie 1d
    // arrayList, bo chcę mieć zachowaną kolejność

    ArrayList<JLabel> labels = new ArrayList<>();


    MapPanel(WorldMap worldMap){

        this.worldMap = worldMap;
        this.worldWidth = worldMap.getWorldWidth();
        this.worldHeight = worldMap.getWorldHeight();
        worldMap.addObserver(this);

        //this.setPreferredSize(new Dimension(worldWidth, worldHeight));
        this.setLayout(new GridLayout(worldWidth, worldHeight));

        initializeLabels();
        this.setVisible(true);


    }
    // musimy się dostać to
    private void initializeLabels(){

        for (int i=0; i< worldHeight; i++){
            for (int j=0; j < worldWidth; j++){
                JLabel label = new JLabel();
                label.setBackground(worldMap.colorOnPosition(new Vector2d(i,j)));
                label.setOpaque(true);
                labels.add(label);
                this.add(label);
            }
        }

    }

    private int getIndex(int x, int y){
        return x*worldWidth+y;
    }


    public void changeElements(Vector2d position){
        int idx = getIndex(position.x, position.y);
        JLabel label = labels.get(idx);
        Color color= worldMap.colorOnPosition(position);
        if (color != null){
            label.setBackground(color);
        }

    }



}
