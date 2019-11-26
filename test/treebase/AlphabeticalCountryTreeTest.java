package treebase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AlphabeticalCountryTreeTest {

    AlphabeticalCountryTree testSubject = new AlphabeticalCountryTree();
    Country a = new Country("Portugal", "Europe", 10, "Lisbon", 38.46, 9.9);
    Country b = new Country("Spain", "Europe", 47, "Madrid", 40.26, 3.42);
    Country c = new Country("France", "Europe", 67, "Paris", 48.51, 2.21);
    Country d = new Country("Brazil", "South America", 210, "Brasilia", 15.47, 47.52);

    @Before
    public void setUp() throws Exception {
        c.addBorder("Spain");
        c.addBorder("Germany");
        testSubject.getTree().insert(a);
        testSubject.getTree().insert(b);
        testSubject.getTree().insert(c);
        testSubject.getTree().insert(d);
    }

    @Test
    public void getTree() {
        AVL avlTree = testSubject.getTree();
        List<Country> avlList = (List<Country>) StreamSupport.stream(avlTree.inOrder().spliterator(), false).collect(Collectors.toList());
        Assert.assertTrue(avlList.contains(a));
        Assert.assertTrue(avlList.contains(b));
        Assert.assertTrue(avlList.contains(c));
        Assert.assertTrue(avlList.contains(d));
        System.out.println(avlTree);
    }

    @Test
    public void getBorders() {
        ArrayList testC = c.getBorders();
        ArrayList testA = a.getBorders();
        Assert.assertTrue(testC.contains("Spain"));
        Assert.assertTrue(testC.contains("Germany"));
        Assert.assertTrue(testA.isEmpty());
    }

    @Test
    public void getOrderedInContinent() {
        ArrayList result = testSubject.getOrderedInContinent("Europe");
        System.out.println(result);
        Assert.assertEquals(result.toString(), "[Portugal, Spain, France]");
    }
}