package Input;

import treebase.AVL;
import treebase.Country;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInput {

    public static void readDataFiles(bstree givenTree) throws FileNotFoundException
    {
        AVL tree = givenTree.getTree();
        Scanner countryInput = new Scanner(new File("paises.txt"));
        Scanner borderInput = new Scanner(new File("fronteiras.txt"));

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

                String borderLine = borderInput.nextLine();
                if (borderLine.trim().length() > 0)
                {
                    String[] borderContent = borderLine.split(",");
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
                tree.insert(cont);
            }
        }
    }
}


