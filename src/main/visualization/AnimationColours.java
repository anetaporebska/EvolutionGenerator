package main.visualization;

import java.awt.*;

public class AnimationColours {

    // flora colors
    public final Color jungleColor = new Color(133, 255, 77);
    public final Color savannaColor = new Color(238, 255, 230);
    public final Color grassColor = new Color(40, 128, 0);

    public final Color deadAnimal = new Color(0, 0, 0);

    // 0 - 50% of initial energy
    private final Color energy50 = new Color(255, 204, 204);

    // 50 -100 % of initial energy
    private final Color energy100 = new Color(255, 153, 153);

    // 100 - 150 % of initial energy
    private final Color energy150 = new Color(255, 102, 102);

    // 150 - 200 % of initial energy
    private final Color energy200 = new Color(255, 51, 51);

    // 200 - 250 % of initial energy
    private final Color energy250 = new Color(255, 0, 0);

    // more than 250% of initial energy
    private final Color energy300 = new Color(204, 0, 0);

    public final Color followed = new Color(8, 29, 141);


    public Color getAnimalColor(int initialEnergy, int animalEnergy){
        float ratio = (float) animalEnergy/initialEnergy;

        if(ratio < 0.5){
            return energy50;
        }
        if(ratio < 1.0){
            return energy100;
        }
        if(ratio < 1.5){
            return energy150;
        }
        if (ratio < 2.0){
            return energy200;
        }
        if (ratio < 2.5){
            return energy250;
        }
        return energy300;



    }




}
