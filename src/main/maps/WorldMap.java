package main.maps;

import main.elements.Animal;
import main.elements.Genome;
import main.elements.Grass;
import main.enums.AnimalOrientation;
import main.interfaces.IEngine;
import main.interfaces.IPositionChangeObserver;
import main.interfaces.IWorldMap;
import main.math.Randomizer;
import main.math.Statistics;
import main.math.Vector2d;
import main.visualization.AnimationColours;
import main.visualization.MapPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class WorldMap implements IWorldMap, IPositionChangeObserver {
    // TODO dodać resztę metod do IWorldMap


    private Vector2d worldMapUpperRight;
    private Vector2d worldMapLowerLeft;

    private Vector2d jungleUpperRight;
    private Vector2d jungleLowerLeft;

    private Map<Vector2d, Grass> grass = new LinkedHashMap<>();
    private Map<Vector2d, ArrayList<Animal>> animals = new LinkedHashMap<>();

    private Randomizer randomizer = new Randomizer();
    private AnimationColours animationColours = new AnimationColours();
    private IEngine engine;
    private MapPanel mapPanel;
    private WorldParameters worldParameters;
    private Statistics statistics;


    public WorldMap(int mapWidth, int mapHeight){
        this.worldMapUpperRight = new Vector2d(mapWidth-1, mapHeight-1);
        this.worldMapLowerLeft = new Vector2d(0,0);
    }

    public WorldMap(WorldParameters worldParameters, Statistics statistics){
        this.worldParameters = worldParameters;
        this.worldMapLowerLeft = new Vector2d(0,0);

        this.statistics = statistics;

        int mapWidth = worldParameters.getWorldWidth();
        int mapHeight = worldParameters.getWorldHeight();
        int jungleWidth = worldParameters.getJungleWidth();
        int jungleHeight = worldParameters.getJungleHeight();

        this.worldMapUpperRight = new Vector2d(mapWidth-1, mapHeight-1);
        int x = (mapWidth - jungleWidth)/2;
        int y = (mapHeight - jungleHeight)/2;

        this.jungleLowerLeft = new Vector2d(x,y);
        this.jungleUpperRight = new Vector2d(x+jungleWidth, y+jungleHeight);
    }



    public Animal placeAnimal(int initialEnergy){
        Vector2d position = randomizer.randomizeElementPosition(worldMapLowerLeft, worldMapUpperRight);
        Animal animal = new Animal(position, initialEnergy, this);
        if (animals.get(position) == null) {
            ArrayList<Animal> arrayList = new ArrayList<>();
            arrayList.add(animal);
            animals.put(position, arrayList);
        }
        else{
            ArrayList<Animal> arrayList = animals.get(position);
            arrayList.add(animal);
            animals.put(position, arrayList);
        }
        animal.addObserver(this);
        return animal;

    }

    private void addAnimal(Animal animal){
        Vector2d position = animal.getPosition();
        if (animals.get(position) == null) {
            ArrayList<Animal> arrayList = new ArrayList<>();
            arrayList.add(animal);
            animals.put(position, arrayList);
        }
        else{
            ArrayList<Animal> arrayList = animals.get(position);
            arrayList.add(animal);
            animals.put(position, arrayList);

        }
        animal.addObserver(this);
        engine.addAnimal(animal);
        notifyMapPanel(position);
    }

    public void addGrass(){
        // Jak 5 razy się nie udało nigdzie postawić trawy, to trudno, nie zostanie dodana -> to nie był problem nieskończonej pętli jbc
        Vector2d newPosition = randomizer.randomizeElementPosition(worldMapLowerLeft, worldMapUpperRight);
        int i=0;
        while (isOccupied(newPosition) || insideJungle(newPosition)){
            newPosition = randomizer.randomizeElementPosition(worldMapLowerLeft, worldMapUpperRight);
            i+=1;
            if (i>5){
                break;
            }
        }
        if(!isOccupied(newPosition) && !insideJungle(newPosition)){
            grass.put(newPosition, new Grass(this, newPosition));
            notifyMapPanel(newPosition);
        }
        i=0;

        newPosition = randomizer.randomizeElementPosition(jungleLowerLeft, jungleUpperRight);
        while (isOccupied(newPosition)){
            newPosition = randomizer.randomizeElementPosition(jungleLowerLeft,jungleUpperRight);
            i+=1;
            if(i>5){
                break;
            }
        }
        if(!isOccupied(newPosition)){
            grass.put(newPosition, new Grass(this, newPosition));
            notifyMapPanel(newPosition);
        }

    }
    public boolean canMoveTo(Vector2d position){
        return (position.x <= worldMapUpperRight.x && position.x >= worldMapLowerLeft.x && position.y <= worldMapUpperRight.y && position.y >= worldMapLowerLeft.y);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        ArrayList<Animal> arrayList = animals.get(oldPosition);
        for (Animal element: arrayList){
            if (element.equals(animal)){
                arrayList.remove(animal);
                break;
            }
        }
        if (arrayList.toArray().length ==0){
            animals.remove(oldPosition);
        }

        if (animals.get(newPosition) == null){
            ArrayList<Animal> arrayList1 = new ArrayList<>();
            arrayList1.add(animal);
            animals.put(newPosition, arrayList1);
        }
        else {
            animals.get(newPosition).add(animal);
        }
        notifyMapPanel(oldPosition);
        notifyMapPanel(newPosition);

    }

    public Vector2d getWorldMapUpperRight(){
        return worldMapUpperRight;
    }

    public Vector2d getWorldMapLowerLeft(){
        return worldMapLowerLeft;
    }

    public boolean isOccupied(Vector2d position){
        if (animals.get(position) != null || grass.get(position)!=null){
            return true;
        }
        return false;
    }

    public Object objectAt(Vector2d position){
        if(animals.get(position) != null &&  animals.get(position).toArray().length >0){
            return animals.get(position).get(0);
        }
        return grass.get(position);
    }

    public void updateAnimalOrientations(){
        for (Vector2d vector2d : animals.keySet()){
            for (Animal animal : animals.get(vector2d)){
                int randomGene = randomizer.randomizeAnimalOrientation(animal, animal.getGenome().getGenes());
                for (int i=0; i<randomGene; i++){
                    AnimalOrientation actualOrientation = animal.getOrientation();
                    actualOrientation = actualOrientation.next();
                    animal.changeAnimalOrientation(actualOrientation);
                }
            }
        }
    }

    public void eatGrass(int energyFromGrass){
        ArrayList<Vector2d> grassToBeRemoved = new ArrayList<>();
        for (Vector2d grassPosition : grass.keySet()){
            if (animals.get(grassPosition) != null){
                int maxEnergy = -1;
                for (Animal animal : animals.get(grassPosition)){
                    if(animal.getAnimalEnergy()>maxEnergy){
                        maxEnergy = animal.getAnimalEnergy();
                    }
                }
                ArrayList<Animal> animalsWithMaxEnergy = new ArrayList<>();
                for (Animal animal : animals.get(grassPosition)){
                    if(animal.getAnimalEnergy()==maxEnergy){
                        animalsWithMaxEnergy.add(animal);
                    }
                }
                int numberOfAnimals = animalsWithMaxEnergy.toArray().length;
                if (numberOfAnimals>0) {
                    int gainedEnergy = energyFromGrass / numberOfAnimals;
                    for (Animal animal : animalsWithMaxEnergy) {
                        animal.gainEnergy(gainedEnergy);
                    }
                    grassToBeRemoved.add(grassPosition);
                }
            }
        }
        for (Vector2d grassPosition : grassToBeRemoved){
            grass.remove(grassPosition);
            notifyMapPanel(grassPosition);
        }
    }

    public void removeAnimal(Animal animal){
        ArrayList<Animal> arrayList = animals.get(animal.getPosition());
        Vector2d position = animal.getPosition();
        arrayList.remove(animal);
        if (arrayList.size()==0){
            animals.remove(position);
        }
        notifyMapPanel(position);
    }

    public int getNumberOfGrass(){
        return grass.keySet().size();
    }

    public void reproductionOfAnimals(){
        ArrayList<Animal> animalsToAdd = new ArrayList<>();
        int requiredEnergy = worldParameters.getInitialEnergy()/2;
        for (Vector2d vector2d : animals.keySet()){
            ArrayList<Animal> animalsOnPosition = animals.get(vector2d);
            if (animalsOnPosition.toArray().length==2){
                Animal animal1 = animalsOnPosition.get(0);
                Animal animal2 = animalsOnPosition.get(1);
                if(animal1.getAnimalEnergy()>requiredEnergy && animal2.getAnimalEnergy()>requiredEnergy ) {
                    Animal animalChild = mixAnimals(animal1, animal2, vector2d);
                    animalsToAdd.add(animalChild);
                }

            }

            else if (animalsOnPosition.toArray().length>2){
                Animal animal1 = findTheStrongest(100000, animalsOnPosition);
                Animal animal2 = null;
                for(Animal animal : animalsOnPosition){
                    if (animal.getAnimalEnergy() == animal1.getAnimalEnergy()){
                        animal2 = animal;
                    }
                }
                if (animal2==null) {
                    animal2 = findTheStrongest(animal1.getAnimalEnergy(), animalsOnPosition);
                }
                if(animal1.getAnimalEnergy()>requiredEnergy && animal2.getAnimalEnergy()>requiredEnergy ) {
                    Animal animalChild = mixAnimals(animal1, animal2, vector2d);
                    animalsToAdd.add(animalChild);
                }
            }
        }
        for(Animal animal: animalsToAdd){
            addAnimal(animal);
            notifyMapPanel(animal.getPosition());
        }
    }

    public Animal findTheStrongest(int upperEnergyLimit, ArrayList<Animal> animals){
        Animal strongestAnimal = null;
        int energy = -1;
        for(Animal animal: animals){
            int animalEnergy = animal.getAnimalEnergy();
            if(animalEnergy > energy && animalEnergy < upperEnergyLimit ){
                strongestAnimal = animal;
                energy = animalEnergy;
            }
        }
        return strongestAnimal;
    }


    private Animal mixAnimals(Animal animal1, Animal animal2, Vector2d position){

        Genome childGenome = Genome.mixGenomes(animal1.getGenome(), animal2.getGenome());

        int energy1 = animal1.getAnimalEnergy()/4;
        int energy2 = animal2.getAnimalEnergy()/4;

        animal1.useEnergy(energy1);
        animal2.useEnergy(energy2);

        int i = 0;
        AnimalOrientation newOrientation = randomizer.randomOrientation();
        Vector2d newPosition = position.add(newOrientation.toUnitVector());
        while (isOccupied(newPosition) || !canMoveTo(newPosition)){
            newOrientation = newOrientation.next();
            newPosition = position.add(newOrientation.toUnitVector());
            i+=1;
            if (i==8){      // all positions are occupied
                while(!canMoveTo(newPosition)){ // jeśli wychodzi poza mapę
                    newOrientation = newOrientation.next();
                    newPosition = position.add(newOrientation.toUnitVector());
                }
                break;
            }
        }
        Animal child = new Animal(newPosition,energy1+energy2, this, childGenome);
        statistics.addChild(animal1,animal2,child);
        return child;
    }

    public void addObserver(IEngine engine){
        this.engine = engine;
    }

    public void addObserver(MapPanel mapPanel){
        this.mapPanel = mapPanel;
    }

    public Color colorOnPosition(Vector2d position){
        Object object = objectAt(position);
        if (object instanceof Animal){
            Animal animal = findTheStrongest(10000, animals.get(position));
            if(animal.getAnimalEnergy()<=0){
                return animationColours.deadAnimal;
            }
            return animationColours.getAnimalColor(worldParameters.getInitialEnergy(), animal.getAnimalEnergy());
        }
        else if (object instanceof Grass){
            return animationColours.grassColor;
        }
        else{
            if (insideJungle(position)){
                return animationColours.jungleColor;
            }
        }
        return animationColours.savannaColor;
    }

    private boolean insideJungle(Vector2d position){
        return (position.x <= jungleUpperRight.x && position.x >= jungleLowerLeft.x && position.y <= jungleUpperRight.y && position.y >= jungleLowerLeft.y);
    }

    public int getWorldWidth(){
        return worldParameters.getWorldWidth();
    }

    public int getWorldHeight(){
        return worldParameters.getWorldHeight();
    }

    public void notifyMapPanel(Vector2d position){
        if (mapPanel!=null) {
            this.mapPanel.changeElements(position);
        }
    }

    public void updateStatistics(){
        if (statistics!=null){
            statistics.updateStatistics(animals, grass);
            statistics.setAverageNumberOfChildren(animals);
        }

    }

    public void initializeStatisticsTree(){
        statistics.initializeFamilyTree(animals);
    }

    public String displayAnimalGenome(Vector2d position){
        if (animals.get(position).size()==1){
            return animals.get(position).get(0).getGenome().displayGenome();
        }
        else if(animals.get(position).size()>1){
            Animal animal = findTheStrongest(10000, animals.get(position));
            return animal.getGenome().displayGenome();
        }
        return "There is no animal";
    }

}
