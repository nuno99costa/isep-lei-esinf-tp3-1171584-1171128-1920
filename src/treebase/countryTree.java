package treebase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class countryTree {

    AVL tree = null;

    /**
     * Empty Constructor
     */
    public countryTree() {
        tree = new AVL();
    }

    /**
     * get AVL tee
     *
     * @return avl tree
     */
    public AVL getTree() {
        return tree;
    }

    /**
     * Returns list of borders of country (null if country doesn't exist)
     *
     * @param country - name of country
     * @return array list containing names of bordering countries
     */
    public ArrayList getBorders(String country) {
        for (Object o : tree.inOrder()) {
            Country inSet = (Country) o;
            if (inSet.getName().equals(country)) {
                return inSet.getBorders();
            }
        }
        return null;
    }


    /**
     * get ordered list of countries of defined continent by n of borders, then population
     *
     * @param continent continent to filter countries by
     * @return array list ordered by number of borders and population
     */
    public ArrayList getOrderedInContinent(String continent) {
        Iterator set = tree.inOrder().iterator();
        ArrayList<Country> countryList = new ArrayList<>();
        while (set.hasNext()) {
            Country inSet = (Country) set.next();
            if (inSet.getContinent().equals(continent)) {
                countryList.add(inSet);
            }
        }

        Comparator<Country> bordersThenPopulation = (o, t1) -> {
            if (o.getBorderNumber() != t1.getBorderNumber()) {
                return Integer.compare(o.getBorderNumber(), t1.getBorderNumber());
            } else {
                return Float.compare(o.getPopulation(), t1.getPopulation());
            }
        };

        countryList.sort(bordersThenPopulation);
        return countryList;
    }
}
