package treebase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class KDTTest {

    LatitudeLongitude2DTree lltree = new LatitudeLongitude2DTree();
    KDT tree = lltree.getTree();
    ArrayList<Country> countries = new ArrayList<Country>();
    Country a = new Country("Portugal", "Europa", 35, "Lisboa", 14.27, 25.89);
    Country b = new Country("Franca", "Europa", 12, "Paris", 56.12, 48.02);
    Country c = new Country("Espanha", "Europa", 90, "Madrid", 17.65, 61.95);
    Country d = new Country("Argentina", "America Sul", 32, "Buenos Aires", 53.61, 21.32);
    Country e = new Country("Equador", "Quito", 17, "Lisboa", 74.83, 81.40);


    @Before
    public void setUp() {
        countries.add(a);
        countries.add(b);
        countries.add(c);
        countries.add(d);
        countries.add(e);

        for (Country i : countries)
            tree.insert(i);
    }

    /*@Test
    public void addComparator() {
    }

    @Test
    public void getComparator() {
    }

    @Test
    public void defineBoundingBoxes() {
    }*/

    @Test
    public void insert() {
        LatitudeLongitude2DTree ll2 = new LatitudeLongitude2DTree();
        KDT tree2 = ll2.getTree();
        tree2.insert(a);
        tree2.insert(b);
        tree2.insert(c);
        tree2.insert(d);
        tree2.insert(e);

        assertEquals(tree.toString(), tree2.toString());
    }


    @Test
    public void remove() {
        LatitudeLongitude2DTree ll2 = new LatitudeLongitude2DTree();
        KDT tree2 = ll2.getTree();
        tree2.insert(a);
        tree2.insert(b);
        tree2.insert(c);
        tree2.insert(d);

        LatitudeLongitude2DTree ll3 = new LatitudeLongitude2DTree();
        KDT tree3 = ll3.getTree();
        tree3.insert(a);
        tree3.insert(b);
        tree3.insert(d);
        tree3.insert(e);

        tree.remove(e);
        assertEquals(tree.toString(), tree2.toString());

        tree.insert(e);
        tree.remove(c);
        assertEquals(tree.toString(), tree3.toString());
    }

    @Test
    public void posOrderNode() {
    }

    @Test
    public void testToString() {
    }
}