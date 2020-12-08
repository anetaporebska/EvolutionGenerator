package main;

import main.maps.WorldMap;
import main.utilities.SimulationEngine;

public class World {
    public static void main(String [] args){

        // WARTOŚCI POCZĄTKOWE
        int initialEnergy = 10;
        int initialNoAnimals = 20;
        int initialNoGrass = 10; // zawsze parzystą poproszę
        int jungleWidth = 5;
        int jungleHeight = 5;
        int worldWidth = 15;
        int worldHeight = 15;
        int energyFromGrass = 3;
        int numberOfDays = 10;

        WorldMap map = new WorldMap(worldWidth, worldHeight, jungleWidth, jungleHeight);
        SimulationEngine engine = new SimulationEngine(map, initialNoAnimals, initialEnergy, initialNoGrass);
        System.out.println(map.toString());
        engine.runDays(numberOfDays, energyFromGrass);


        //TODO efektywna struktura pozwalająca mi na przechowywanie informacji o wszystkich zwierzątkach na danej pozycji


        // TODO klasa Genome
        // każdy Animal ma Genome


    }
}


/*
Jakiś plan działania czy coś

Będę potrzebować mapę, na której na środku znajduje się dżungla =
rośliny wyrastają losowo, ale w dźungli częściej
codziennie dwie nowe rośliny - po jednej na stepie i w dżungli

Zwierzę ma określoną energię, która zmniejsza się każdego dnia

jeśli energia zwięrzęcia spadnie poniżej 0 to zwierzę umiera
energia = ile dni bez jedzenia zwierzę nam przeżyje
każdego dnia dodatkowo zwierzę zmienia swoją pozycję - mamy 8 mozliwych obrotów - zwierzę zawsze idzie do przodu

jeśli zwierzę stanie na polu z roślina to poziom energii zwiększa się o pewną wartość

każde zwierzę ma genom - 32 geny składające się z jednej liczby w zakresie od 0 do 7

czyli trawa znika po zjedzeniu


 */