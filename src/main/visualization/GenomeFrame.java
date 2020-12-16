package main.visualization;


import javax.swing.*;

public class GenomeFrame extends JFrame {

    GenomeFrame(String genome, int x, int y){
        JLabel textLabel = new JLabel("Animal on position "+x+","+y+"has genome: ");

        JLabel genomeLabel = new JLabel(genome);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(550,100);
        this.add(textLabel);
        this.add(genomeLabel);
        this.setVisible(true);
    }

}
