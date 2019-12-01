import Input.FileInput;
import treebase.AlphabeticalCountryTree;
import treebase.Country;
import treebase.LatitudeLongitude2DTree;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner read = new Scanner(System.in);
        AlphabeticalCountryTree ex1tree = new AlphabeticalCountryTree();
        FileInput.readDataFiles(ex1tree.getTree());

        //Ex1.a
        System.out.println("Exercício 1-A \n");
        System.out.println("Fronteiras de Russia: " + ex1tree.getBorders("russia"));
        System.out.println("Fronteiras de Brasil: " + ex1tree.getBorders("brasil"));
        System.out.println("Fronteiras de Alemanha: " + ex1tree.getBorders("alemanha"));

        System.out.println("\nPrima qualquer tecla para proceder");
        read.next();
        System.out.println();

        //Ex1.b
        System.out.println("Exercício 1-B \n");
        System.out.println("Países da Europa: " + ex1tree.getOrderedInContinent("europa"));
        System.out.println("Países da América do Sul: " + ex1tree.getOrderedInContinent("americasul"));

        System.out.println("\nPrima qualquer tecla para proceder");
        read.next();
        System.out.println();

        //Ex2.a
        LatitudeLongitude2DTree ex2tree = new LatitudeLongitude2DTree();
        FileInput.readDataFiles(ex2tree.getTree());

        //Ex2.b
        System.out.println("Exercício 2-B \n");
        double lat = 52.5234051;
        double lon = 13.4113999;
        System.out.println("Latitude: " + lat + "\nLongitude: " + lon);
        System.out.println("País: " + ex2tree.exactFind(lat, lon));

        System.out.println("\nPrima qualquer tecla para proceder");
        read.next();
        System.out.println();

        //Ex2.c
        System.out.println("Exercício 2-C \n");
        double latitude = 84;
        double longitude = -11;
        System.out.println("Latitude: " + latitude + "\nLongitude: " + longitude);
        System.out.println("País mais próximo: " + ex2tree.nearestNeighbor(latitude, longitude));

        System.out.println("\nPrima qualquer tecla para proceder");
        read.next();
        System.out.println();

        //Ex2.d
        System.out.println("Exercício 2-D \n");
        double latitude1 = 6;
        double latitude2 = -67;
        double longitude1 = 11;
        double longitude2 = -57;
        System.out.println("Latitude 1: " + latitude1 + "\nLongitude1: " + longitude1);
        System.out.println("Latitude 2: " + latitude2 + "\nLongitude2: " + longitude2);
        System.out.println("Países: " + ex2tree.getCountriesInsideGeoBox(latitude1, longitude1, latitude2, longitude2));

    }

}