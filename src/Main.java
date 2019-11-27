import Input.FileInput;
import treebase.AlphabeticalCountryTree;
import treebase.Country;
import treebase.KDT;
import treebase.LatitudeLongitude2DTree;

import java.io.FileNotFoundException;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        AlphabeticalCountryTree ex1tree = new AlphabeticalCountryTree();
        FileInput.readDataFiles(ex1tree.getTree());

        //Ex1.a
        System.out.println("Exercício 1-A \n");
        System.out.println("Fronteiras de Russia: " + ex1tree.getBorders("russia"));
        System.out.println("Fronteiras de Brasil: " + ex1tree.getBorders("brasil"));
        System.out.println("Fronteiras de Alemanha: " + ex1tree.getBorders("alemanha"));

        LatitudeLongitude2DTree ex2tree = new LatitudeLongitude2DTree();
        Comparator latitude = Comparator.comparingDouble(o -> ((Country) o).getLatitude());
        Comparator longitude = Comparator.comparingDouble(o -> ((Country) o).getLongitude());
        ex2tree.getTree().addComparator(latitude);
        ex2tree.getTree().addComparator(longitude);
        FileInput.readDataFiles(ex2tree.getTree());

        System.out.println(ex2tree.getTree());

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