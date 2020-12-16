package main.math;

import main.elements.Animal;

import java.util.*;

public class FamilyTree {

    // ogromna hash mapa, w której mamy dane zwierzę i jego dzieci -> jak chcę uzyskać potomków, to dzieci + dzieci dzieci + dzieci dzieci dzieci + ...
    private Map<Animal, ArrayList<Animal>> animalChildren = new LinkedHashMap<>();

    public void addAncestors(Map<Vector2d, ArrayList<Animal>> animals){

        for(Vector2d vector2d : animals.keySet()){

            for(Animal animal : animals.get(vector2d)){
                animalChildren.put(animal, new ArrayList<>());
            }

        }

    }

    public void addChild(Animal parent1, Animal parent2, Animal child){

        ArrayList<Animal> parent1Children = animalChildren.get(parent1);
        parent1Children.add(child);

        ArrayList<Animal> parent2Children = animalChildren.get(parent2);
        parent2Children.add(child);

        animalChildren.put(child, new ArrayList<>());

    }


    public int getNumberOfChildren(Animal animal){
        return animalChildren.get(animal).size();
    }

    // trzeba pilnować, żeby nie zapętlić się i liczyć kilka razy dzieci dnaego zwierzątka (bo rodzic może rozmnażać się z dzieckiem)
    public int getNumberOfDescendants(Animal animal){
        // mogęmieć set z potomkami - nie będie powtórzeć
        // od przodka do jego dzieci , nie będzie pętli tylko powtórzenia

        Set<Animal> set = new HashSet<>();

        Queue<Animal> queue = new LinkedList<>();
        queue.add(animal);

        while (!queue.isEmpty()){

            Animal parent = queue.poll();

            for(Animal child: animalChildren.get(parent)){
                queue.add(child);
                set.add(child);
            }
        }
        return set.size();


    }










}
