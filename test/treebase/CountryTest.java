package treebase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CountryTest {

    Country a = new Country("Portugal", "Europa", 10, "Lisboa", 38.46, 9.9);
    Country b = new Country("Espanha", "Europa", 47, "Madrid", 40.26, 3.42);
    Country c = new Country("Franca", "Europa", 67, "Paris", 48.51, 2.21);
    Country d = new Country("Brasil", "America Sul", 210, "Brasilia", 15.47, 47.52);

    @Before
    public void setUp() throws Exception {
        a.addBorder("Espanha");
        a.addBorder("Alemanha");
        a.addBorder("Suecia");

        b.addBorder("Brasil");
        b.addBorder("Noruega");

        c.addBorder("Polonia");
    }

    @Test
    public void testGetName() {
        assertEquals(a.getName(), "Portugal");
    }

    @Test
    public void testSetName() {
        assertEquals(a.getName(), "Portugal");
        try {
            a.setName("Chile");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(a.getName(), "Chile");

    }

    @Test
    public void testGetContinent() {
        assertEquals(a.getContinent(), "Europa");
    }

    @Test
    public void testSetContinent() {
        assertEquals(a.getContinent(), "Europa");
        try {
            a.setContinent("Asia");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(a.getContinent(), "Asia");

    }

    @Test
    public void testGetPopulation() {
        assertEquals(a.getPopulation(), 10, 0);
    }

    @Test
    public void testSetPopulation() {
        assertEquals(a.getPopulation(), 10, 0);
        try {
            a.setPopulation(25);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(a.getPopulation(), 25, 0);

    }

    @Test
    public void testGetCapital() {
        assertEquals(a.getCapital(), "Lisboa");
    }

    @Test
    public void testSetCapital() {
        assertEquals(a.getCapital(), "Lisboa");
        try {
            a.setCapital("Madrid");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(a.getCapital(), "Madrid");

    }

    @Test
    public void testGetLatitude() {
        assertEquals(a.getLatitude(), 38.46, 0);
    }

    @Test
    public void testSetLatitude() {
        assertEquals(a.getLatitude(), 38.46, 0);
        try {
            a.setLatitude(47.81);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(a.getLatitude(), 47.81, 0);

    }

    @Test
    public void testGetLongitude() {
        assertEquals(a.getLongitude(), 9.9, 0);
    }

    @Test
    public void testSetLongitude() {
        assertEquals(a.getLongitude(), 9.9, 0);
        try {
            a.setLongitude(31.65);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(a.getLongitude(), 31.65, 0);

    }

    @Test
    public void testGetBorders() {
        ArrayList temp = a.getBorders();
        ArrayList<String> res = new ArrayList<>();
        res.add("Espanha");
        res.add("Alemanha");
        res.add("Suecia");
        assertEquals(temp,res);

    }

    @Test
    public void testAddBorder() {
        ArrayList temp = a.getBorders();
        assertFalse(temp.contains("Lituania"));
        temp.add("Lituania");
        assertTrue(temp.contains("Lituania"));
    }

    @Test
    public void testGetBorderNumber() {
        assertEquals(a.getBorderNumber(), 3);
        assertEquals(b.getBorderNumber(), 2);
        assertEquals(c.getBorderNumber(), 1);
        assertEquals(d.getBorderNumber(), 0);

        d.addBorder("Argentina");
        assertEquals(d.getBorderNumber(), 1);
    }

}