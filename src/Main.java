import Input.FileInput;
import graphbase.Graph;
import graphbase.GraphAlgorithms;
import treebase.Country;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.TreeMap;

public class Main {

    public static <V, E> void main(String[] args) throws FileNotFoundException {
        Graph grafo = new Graph(false);
        FileInput.readDataFiles(grafo);

        //Ex2
        TreeMap<V, Integer> resultado = new TreeMap<V, Integer>();
        resultado = GraphAlgorithms.MapColoring(grafo);
        String color = null;
        System.out.println("EXERCICIO 2\n\n");

        for (V v : resultado.keySet()) {
            switch (resultado.get(v)) {
                case 0:
                    color = "verde";
                    break;
                case 1:
                    color = "azul";
                    break;
                case 2:
                    color = "vermelho";
                    break;
                case 3:
                    color = "laranja";
                    break;
                default:
                    color = " não definida";
            }
            System.out.println(((Country) v).getName() + " - " + color);
        }


        System.out.println("\n\nEXERCICIO 3\n\n");
        Country f = grafo.getCountryWithName("grecia");
        Country b = grafo.getCountryWithName("russia");
        LinkedList caminho = new LinkedList();
        double dist = GraphAlgorithms.shortestPath(grafo, f, b, caminho);

        System.out.println("inicio - " + f.toString());
        System.out.println("fim - " + b.toString());
        System.out.println("distancia percorrida - " + dist + "km");
        System.out.println("Caminho:");
        for (Object v : caminho) {
            System.out.println(((Country) v).getName());
        }

        System.out.println("\n\nEXERCICIO 4\n\n");
        Country origin = grafo.getCountryWithName("alemanha");
        Country destination = grafo.getCountryWithName("noruega");
        Country[] stops = new Country[9];
        LinkedList path = new LinkedList<>();
        int i = 0;

        System.out.println("inicio - " + origin.toString());
        System.out.println("fim - " + destination.toString());
        stops[0] = grafo.getCountryWithName("grecia");
        System.out.println("paragem - " + stops[0].toString());
        stops[1] = grafo.getCountryWithName("hungria");
        System.out.println("paragem - " + stops[1].toString());
        stops[2] = grafo.getCountryWithName("belgica");
        System.out.println("paragem - " + stops[2].toString());
        stops[3] = grafo.getCountryWithName("liechtenstein");
        System.out.println("paragem - " + stops[3].toString());
        stops[4] = grafo.getCountryWithName("italia");
        System.out.println("paragem - " + stops[4].toString());
        stops[5] = grafo.getCountryWithName("luxemburgo");
        System.out.println("paragem - " + stops[5].toString());
        stops[6] = grafo.getCountryWithName("macedonia");
        System.out.println("paragem - " + stops[6].toString());
        stops[7] = grafo.getCountryWithName("bielorussia");
        System.out.println("paragem - " + stops[7].toString());
        stops[8] = grafo.getCountryWithName("polonia");
        System.out.println("paragem - " + stops[8].toString());

        double dist1 = GraphAlgorithms.shortestPathWithStops(grafo, origin, destination, stops, path);

        System.out.println("distancia percorrida - " + dist1 + "km");
        System.out.println("Caminho:");
        for (Object v : path) {
            System.out.println(((Country) v).getName());
        }

        System.out.println("\n\nEXERCICIO 5\n\n");

        origin = grafo.getCountryWithName("franca");
        path = GraphAlgorithms.largestCycleWithStart(grafo, origin);


        System.out.println("Caminho (tamanho " + path.size() + "):");
        for (Object v : path) {
            System.out.println(((Country) v).getName());
        }

    }

}