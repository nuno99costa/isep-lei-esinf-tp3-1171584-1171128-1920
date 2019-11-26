import treebase.AVL;
import treebase.Country;

import java.util.ArrayList;
import java.util.TreeSet;

public class bstree {

    AVL tree = null;

    public bstree() {
        tree = new AVL();
    }

    public AVL getTree() {
        return tree;
    }

    public ArrayList getFrontiers(Country a) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public TreeSet getOrderedInContinent(String continent) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
