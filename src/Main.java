import Input.FileInput;
import treebase.AlphabeticalCountryTree;
import treebase.KDT;

import java.io.FileNotFoundException;
import java.util.Comparator;

public class Main {

    public static <V, E> void main(String[] args) throws FileNotFoundException {
        AlphabeticalCountryTree ex1tree = new AlphabeticalCountryTree();
        FileInput.readDataFiles(ex1tree);

        //Ex1.a
        System.out.println("Exercício 1-A \n");
        System.out.println("Fronteiras de Russia: " + ex1tree.getBorders("russia"));
        System.out.println("Fronteiras de Brasil: " + ex1tree.getBorders("brasil"));
        System.out.println("Fronteiras de Alemanha: " + ex1tree.getBorders("alemanha"));

        //kd-test
        KDT a = new KDT(2);
        Comparator b = Comparator.comparing(o -> ((Integer) o));
        Comparator c = (o, t1) -> ((Integer) o).compareTo((Integer) t1) * -1;

        a.addComparator(b);
        a.addComparator(c);

        a.insert(12);
        a.insert(14);
        a.insert(17);
        a.insert(11);
        a.insert(15);
        a.insert(16);
        a.insert(20);

        System.out.println(a);

        a.remove(13);

        System.out.println(a);
    }

}