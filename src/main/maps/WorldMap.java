package main.maps;

import main.Randomizer;
import main.World;
import main.elements.Animal;
import main.elements.Grass;
import main.enums.AnimalOrientation;
import main.interfaces.IPositionChangeObserver;
import main.interfaces.IWorldMap;
import main.math.Vector2d;
import main.utilities.MapVisualizer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorldMap implements IWorldMap, IPositionChangeObserver {
    // TODO dodać resztę metod do IWorldMap


    private Vector2d worldMapUpperRight;
    private Vector2d worldMapLowerLeft;

    private Vector2d jungleUpperRight;
    private Vector2d jungleLowerLeft;


    // TODO zastanowić się czy nie wolę mieć wszystkich elementów w jednej linkedHashMap przez MapElement

    // lista dla trawy - na jednej pozycji może być tylko jedna trawa
    protected Map<Vector2d, Grass> grass = new LinkedHashMap<>();

    // lista dla zwierząt
    protected Map<Vector2d, ArrayList<Animal>> animals = new LinkedHashMap<>();

    private Randomizer randomizer = new Randomizer();

    public WorldMap(Vector2d worldMapLowerLeft, Vector2d worldMapUpperRight, Vector2d jungleLowerLeft, Vector2d jungleUpperRight){
        this.worldMapLowerLeft = worldMapLowerLeft;
        this.worldMapUpperRight = worldMapUpperRight;
        this.jungleLowerLeft = jungleLowerLeft;
        this.jungleUpperRight = jungleUpperRight;
    }

    public WorldMap(int mapWidth, int mapHeight, int jungleWidth, int jungleHeight){
        this.worldMapLowerLeft = new Vector2d(0,0);
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
    // każdego dnia dodajemy 2 trawy, jedna w dżungli, druga na sawannie
    // w jednym miejscu może być jedna trawa, trawa nie może się pojawić gdzieś gdzie już coś jest (zwierzę lub trawa)
    // metoda do dodawania trawy po każdym dniu
    public void addGrass(){
        // najpierw w dżungli
        Vector2d newPosition = randomizer.randomizeElementPosition(jungleLowerLeft, jungleUpperRight);
        while (isOccupied(newPosition)){
            newPosition = randomizer.randomizeElementPosition(jungleLowerLeft,jungleUpperRight);
        }
        grass.put(newPosition, new Grass(this, newPosition));

        // na sawannie
        newPosition = randomizer.randomizeElementPosition(worldMapLowerLeft, worldMapUpperRight);
        while (isOccupied(newPosition) && !outsideJungle(newPosition)){
            newPosition = randomizer.randomizeElementPosition(worldMapLowerLeft, worldMapUpperRight);
        }
        grass.put(newPosition, new Grass(this, newPosition));


    }
    // jak wylosuję pozycję dla trawy na sawannie
    private boolean outsideJungle(Vector2d position){
        return !(position.x <= jungleUpperRight.x && position.x >= jungleLowerLeft.x && position.y <= jungleUpperRight.y && position.y >= jungleLowerLeft.y);
    }

   // public void nextDay(){
        // moveAnimals + updateEnergy
        // reproductionOfAnimals
        // addGrass()
        // removeDeadAnimals
    //}
    // tylko dla zwierzęcia, wystarczy sprawdzić czy nie wychodzi poza mapę

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

    }

    public Vector2d getWorldMapUpperRight(){
        return worldMapUpperRight;
    }

    public Vector2d getWorldMapLowerLeft(){
        return worldMapLowerLeft;
    }

    public String toString(){
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(worldMapLowerLeft, worldMapUpperRight);

    }

    public boolean isOccupied(Vector2d position){
        if (animals.get(position) != null || grass.get(position)!=null){
            return true;
        }
        return false;
    }

    public Object objectAt(Vector2d position){
        if(animals.get(position) != null &&  animals.get(position).toArray().length >0){
            return animals.get(position).get(0); // zracam pierwsze lepsze zwierzę
        }
        return grass.get(position);
    }

    public void updateAnimalOrientations(){
        for (Vector2d vector2d : animals.keySet()){

            for (Animal animal : animals.get(vector2d)){
                int randomGene = randomizer.randomizeAnimalOrientation(animal, animal.genome);
                // taki jaki gen został wylosowany, tyle razy obracam zwierzę w prawo o 45 stopni
                for (int i=0; i<randomGene; i++){
                    AnimalOrientation actualOrientation = animal.getOrientation();
                    actualOrientation = actualOrientation.next();
                    animal.changeAnimalOrientation(actualOrientation);
                }


            }


        }


    }

    public void eatGrass(int energyFromGrass){
        // będę sprawdzać pozycję każdej trawy i sprawdzać czy nie stoją na jej polu jakieś zwierzątka,
        // trawę zjada to, które ma najwięcej energii, a jeśli kilka ma taka samą energię to dzielą się po równo
        ArrayList<Vector2d> grassToBeRemoved = new ArrayList<>();
        for (Vector2d grassPosition : grass.keySet()){

            if (animals.get(grassPosition) != null){

                int maxEnergy = 0;
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
                int gainedEnergy = energyFromGrass/numberOfAnimals;
                for (Animal animal : animalsWithMaxEnergy){
                    animal.gainEnergy(gainedEnergy);
                }

                // muszę jeszcze usunąć tą trawę
                grassToBeRemoved.add(grassPosition);
            }

        }
        for (Vector2d grassPosition : grassToBeRemoved){
            grass.remove(grassPosition);
        }


    }

    public void removeDeadAnimals(){
        // przejrzeć wszystkie zwierzęta i sprawdzić czy nie trzeba usunąć jakiegoś które jest martwe
    }




}
