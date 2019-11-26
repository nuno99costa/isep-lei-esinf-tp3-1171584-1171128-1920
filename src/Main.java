import Input.FileInput;
import treebase.countryTree;

import java.io.FileNotFoundException;

public class Main {

    public static <V, E> void main(String[] args) throws FileNotFoundException {
        countryTree ex1tree = new countryTree();
        FileInput.readDataFiles(ex1tree);

        //Ex1.a
        System.out.println("Exercício 1-A \n");
        System.out.println("Fronteiras de Russia: " + ex1tree.getBorders("russia"));
        System.out.println("Fronteiras de Brasil: " + ex1tree.getBorders("brasil"));
        System.out.println("Fronteiras de Alemanha: " + ex1tree.getBorders("alemanha"));
    }

}