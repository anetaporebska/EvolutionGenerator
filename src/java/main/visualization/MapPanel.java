package main.visualization;

import main.map.WorldMap;
import main.math.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MapPanel extends JPanel implements MouseListener {

    int worldWidth;
    int worldHeight;

    private WorldMap worldMap;
    private AnimationColours animationColours = new AnimationColours();
    private ArrayList<JLabel> labels = new ArrayList<>();
    private boolean action = false;
    private boolean follow;

    MapPanel(WorldMap worldMap){

        this.worldMap = worldMap;
        this.worldWidth = worldMap.getWorldWidth();
        this.worldHeight = worldMap.getWorldHeight();
        worldMap.addObserver(this);
        this.setLayout(new GridLayout(worldHeight, worldWidth));

        initializeLabels();
        this.setVisible(true);
    }

    public boolean checkFollow(){
        return this.follow;
    }

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

    public void displayDominant(){

        for (int i=0; i< worldHeight; i++){
            for (int j=0; j < worldWidth; j++){
                JLabel label = labels.get(getIndex(i,j));
                if(worldMap.dominantAnimal(new Vector2d(i,j))){
                    label.setBackground(Color.BLUE);
                }
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

    public void addMouseListeners(boolean displayGenome){
        this.action = displayGenome;
        for (JLabel jLabel : labels){
            jLabel.addMouseListener(this);
        }

    }

    public void deleteMouseListeners(){
        for (JLabel jLabel : labels){
            jLabel.removeMouseListener(this);
        }
    }



    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("Clicked on "+ mouseEvent.getSource().toString());
        JLabel labelClicked = (JLabel) mouseEvent.getSource();

        // display genome of 1 animal
        if (this.action){
            for (int i=0; i< worldHeight; i++){
                for (int j=0; j < worldWidth; j++){
                    int idx = getIndex(i,j);
                    if (labels.get(idx).equals(labelClicked)){
                        String genome =worldMap.displayAnimalGenome(new Vector2d(i,j));
                        new GenomeFrame(genome,i,j);
                        deleteMouseListeners();
                        return;
                    }
                }
            }
        }
        // add animal to follow
        else{
            for (int i=0; i< worldHeight; i++){
                for (int j=0; j < worldWidth; j++){
                    int idx = getIndex(i,j);
                    if (labels.get(idx).equals(labelClicked)){
                        this.follow = worldMap.addToFollow(new Vector2d(i,j));
                        deleteMouseListeners();
                        return;
                    }
                }
            }

        }


    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
