package Input;

import treebase.AVL;
import treebase.Country;
import treebase.KDT;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInput {


    public static void readDataFiles(Object a) throws FileNotFoundException
    {
        KDT KDTree = null;
        AVL AVLTree = null;
        if (a instanceof KDT) {
            KDTree = (KDT) a;
        } else {
            AVLTree = (AVL) a;
        }
        Scanner countryInput = new Scanner(new File("paises.txt"));

        while (countryInput.hasNextLine())
        {
            String line = countryInput.nextLine();
            if (line.trim().length() > 0)
            {
                String[] content = line.split(",");
                String name = content[0].trim();
                String continent = content[1].trim();
                float population = Float.parseFloat(content[2]);
                String capital = content[3].trim();
                double latitude = Double.parseDouble(content[4]);
                double longitude = Double.parseDouble(content[5]);

                Country cont = new Country(name, continent, population, capital, latitude, longitude);
                Scanner borderInput = new Scanner(new File("fronteiras.txt"));
                while (borderInput.hasNextLine()) {
                    String[] borderContent = borderInput.nextLine().split(",");
                    String border1 = borderContent[0].trim();
                    String border2 = borderContent[1].trim();

                    if (name.equals(border1))
                    {
                        cont.addBorder(border2);
                    }

                    if (name.equals(border2))
                    {
                        cont.addBorder(border1);
                    }
                }
                if (a instanceof KDT) {
                    KDTree.insert(cont);
                } else {
                    AVLTree.insert(cont);
                }
            }
        }
        if (a instanceof KDT) {
            KDTree.defineBoundingBoxes();
        }
    }
}


