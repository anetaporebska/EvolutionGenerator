package main.visualization;

// wyświetlanie aktualnej mapy ?


// za każdym razem gdy cokolwiek się zmieni musimy informować o tym mapPanel
// energia zwierzęcia, nowe zwierzę, nowa trawa, trawa zjedzona wszystko !!!

// nieoptymalnie można tworzyć za każdym razem nowy MapPanel

import main.maps.WorldMap;
import main.math.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MapPanel extends JPanel implements MouseListener {

    int worldWidth;
    int worldHeight;

    WorldMap worldMap;

    AnimationColours animationColours = new AnimationColours();


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

    // jak dodaję mouse listener to muszę jessze gdzieś powiedzieć co chcę żeby się działo -> mam 2 opcje
    public void addMouseListeners(){

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

        // znajdź mi tera tego labela
        for (int i=0; i< worldHeight; i++){
            for (int j=0; j < worldWidth; j++){
                int idx = getIndex(i,j);
                if (labels.get(idx).equals(labelClicked)){
                    // wyświetl mi tu zwierzę co to go kliknięto
                    System.out.println("współrzędne");
                    System.out.println(i);
                    System.out.println(j);
                    String genome =worldMap.displayAnimalGenome(new Vector2d(i,j));
                    new GenomeFrame(genome,i,j);
                }
            }
        }


       deleteMouseListeners();



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
