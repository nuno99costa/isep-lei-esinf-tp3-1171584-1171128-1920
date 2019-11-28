import Input.FileInput;
import treebase.AlphabeticalCountryTree;
import treebase.Country;
import treebase.LatitudeLongitude2DTree;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        AlphabeticalCountryTree ex1tree = new AlphabeticalCountryTree();
        FileInput.readDataFiles(ex1tree.getTree());

        //Ex1.a
        System.out.println("Exercício 1-A \n");
        System.out.println("Fronteiras de Russia: " + ex1tree.getBorders("russia"));
        System.out.println("Fronteiras de Brasil: " + ex1tree.getBorders("brasil"));
        System.out.println("Fronteiras de Alemanha: " + ex1tree.getBorders("alemanha"));

        //Ex2
        LatitudeLongitude2DTree ex2tree = new LatitudeLongitude2DTree();
        FileInput.readDataFiles(ex2tree.getTree());

        System.out.println(ex2tree.getTree());

        Country test = ex2tree.nearestNeighbor(0, 0);
        System.out.println(test);
    }

}