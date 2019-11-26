package Input;

import graphbase.Border;
import graphbase.Graph;
import treebase.Country;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInput {

    /**
     * Retrives all the countries and borders from the given files and uses them
     * to build the given graph with the countries as the vertexes and borders
     * as the edges
     *
     * @param countryMap graph to store all the files' information
     * @throws FileNotFoundException
     */
    public static void readDataFiles(Graph countryMap) throws FileNotFoundException {
        Scanner countryInput = new Scanner(new File("paises.txt"));
        Scanner borderInput = new Scanner(new File("fronteiras.txt"));
        ArrayList<Country> countries = new ArrayList<>();

        while (countryInput.hasNextLine()) {
            String line = countryInput.nextLine();
            if (line.trim().length() > 0) {
                String[] content = line.split(",");
                String name = content[0].trim();
                String continent = content[1].trim();
                float population = Float.parseFloat(content[2]);
                String capital = content[3].trim();
                double latitude = Double.parseDouble(content[4]);
                double longitude = Double.parseDouble(content[5]);
                Country temp = new Country(name, continent, population, capital, latitude, longitude);
                countries.add(temp);
                countryMap.insertVertex(temp);
            }
        }

        Country countryOrigin = null;
        Country countryDestination = null;

        while (borderInput.hasNextLine()) {
            String line = borderInput.nextLine();
            if (line.trim().length() > 0) {
                String[] content = line.split(",");
                String c1 = content[0].trim();
                String c2 = content[1].trim();

                for (Country var : countries) {
                    String countryName = var.getName().trim();
                    if (countryName.equals(c1)) {
                        countryOrigin = var;
                    }
                    if (countryName.equals(c2)) {
                        countryDestination = var;
                    }
                }
                Border edgeBorder = new Border(countryOrigin, countryDestination);
                double edgeDistance = distance(countryOrigin, countryDestination);
                countryMap.insertEdge(countryOrigin, countryDestination, edgeBorder, edgeDistance);
            }
        }
    }

    /**
     * Given two Country objects, calculates the distance between them in
     * kilometers (km)
     *
     * @param contOrigin first Country object
     * @param contDest   second Country object
     * @return double value containing countries' distance in km
     */
    private static double distance(Country contOrigin, Country contDest) {

        double lat1 = contOrigin.getLatitude();
        double lon1 = contOrigin.getLongitude();
        double lat2 = contDest.getLatitude();
        double lon2 = contDest.getLongitude();

        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515 * 1.609344;
            return (dist);
        }
    }
}
