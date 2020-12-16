import main.elements.Animal;
import main.math.FamilyTree;
import main.math.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FamilyTreeTest {

    Vector2d position_1_1 = new Vector2d(1,1);
    Vector2d position_3_3 = new Vector2d(3,3);
    Vector2d position_1_3 = new Vector2d(1,3);


    Map<Vector2d, ArrayList<Animal>> animals = new LinkedHashMap<>();
    ArrayList<Animal> arrayList1 = new ArrayList<>();
    ArrayList<Animal> arrayList2 = new ArrayList<>();

    Animal A = new Animal(position_1_1, 10, null);
    Animal B = new Animal(position_1_3, 10, null);
    Animal C = new Animal(position_1_1, 10, null);
    Animal D = new Animal(position_1_3, 10, null);


    Animal E = new Animal(position_3_3, 10, null);
    Animal F = new Animal(position_3_3, 10, null);
    Animal G = new Animal(position_1_1, 10, null);
    Animal H = new Animal(position_1_1, 10, null);
    Animal I = new Animal(position_1_3, 10, null);
    Animal J = new Animal(position_1_3, 10, null);
    Animal K = new Animal(position_1_3, 10, null);
    Animal L = new Animal(position_3_3, 10, null);

    FamilyTree familyTree = new FamilyTree();

    public void initialize(){
        arrayList1.add(A);
        arrayList1.add(C);
        arrayList2.add(B);
        arrayList2.add(D);

        animals.put(position_1_1, arrayList1);
        animals.put(position_1_3, arrayList2);



    }


    @Test
    public void numberOfChildrenTest(){
        initialize();
        familyTree.addAncestors(animals);

        assertEquals(0,familyTree.getNumberOfChildren(A));
        assertEquals(0,familyTree.getNumberOfChildren(B));
        assertEquals(0,familyTree.getNumberOfDescendants(A));
        assertEquals(0,familyTree.getNumberOfDescendants(B));

        familyTree.addChild(A,B,G);
        familyTree.addChild(C,D,E);
        familyTree.addChild(C,E,F);

        assertEquals(1,familyTree.getNumberOfChildren(A));
        assertEquals(2,familyTree.getNumberOfChildren(C));
        assertEquals(2,familyTree.getNumberOfDescendants(D));
        assertEquals(2,familyTree.getNumberOfDescendants(C));

        familyTree.addChild(C,G,H);
        familyTree.addChild(B,F,I);
        familyTree.addChild(D,I,J);
        familyTree.addChild(H,I,L);
        familyTree.addChild(I,J,K);

        assertEquals(1,familyTree.getNumberOfChildren(A));
        assertEquals(3,familyTree.getNumberOfChildren(C));
        assertEquals(6,familyTree.getNumberOfDescendants(D));
        assertEquals(4,familyTree.getNumberOfDescendants(F));

        assertEquals(0,familyTree.getNumberOfChildren(L));
        assertEquals(2,familyTree.getNumberOfChildren(B));
        assertEquals(6,familyTree.getNumberOfDescendants(B));
        assertEquals(0,familyTree.getNumberOfDescendants(K));


    }

}
