package main.visualization;
import javax.swing.*;

public class GenomeFrame extends JFrame {

    public GenomeFrame(String genome, int x, int y){
        JLabel textLabel = new JLabel("Animal on position ("+x+","+y+") has genome: ");
        textLabel.setBounds(0,0,550,100);
        JLabel genomeLabel = new JLabel(genome);
        genomeLabel.setBounds(0,100, 550,100);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(550,200);
        this.add(textLabel);
        this.add(genomeLabel);
        this.setVisible(true);
    }
}
