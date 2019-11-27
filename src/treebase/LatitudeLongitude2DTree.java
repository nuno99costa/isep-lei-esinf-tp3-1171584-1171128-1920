package treebase;

import java.util.Comparator;

public class LatitudeLongitude2DTree {
    KDT tree = null;

    public LatitudeLongitude2DTree() {
        tree = new KDT(2);
        tree.addComparator(Comparator.comparingDouble(o -> ((Country) o).getLatitude()).thenComparing(o -> ((Country) o).getLongitude()));
        tree.addComparator(Comparator.comparingDouble(o -> ((Country) o).getLongitude()).thenComparing(o -> ((Country) o).getLatitude()));
    }

    public KDT getTree() {
        return tree;
    }

    public Country nearestNeighbor(double latitude, double longitude) {
        return nearestNeighbor(latitude, longitude, tree.root, 0, Double.MAX_VALUE);
    }

    private Country nearestNeighbor(double latitude, double longitude, KDT.Node node, int level, double min_distance) {
        Country pos = (Country) node.getElement();
        Comparator current = null;
        //stop condition
        if (node.getRight() != null && node.getLeft() != null) {

        }


        if (pos.getLatitude() == latitude && pos.getLongitude() == longitude) {
            return (Country) node.getElement();
        }


        if (level > 1) {
            level -= 2;
        }
        if (level == 0) {
            current = (o, t1) -> {
                if (Double.compare(((Country) o).getLatitude(), latitude) == 0) {
                    return Double.compare(((Country) o).getLongitude(), longitude);
                }
                return Double.compare(((Country) o).getLatitude(), latitude);
            };
        } else {
            current = (o, t1) -> {
                if (Double.compare(((Country) o).getLongitude(), longitude) == 0) {
                    return Double.compare(((Country) o).getLatitude(), latitude);
                }
                return Double.compare(((Country) o).getLongitude(), longitude);
            };
        }
        if (current.compare(node.getElement(), null) > 0) {
            nearestNeighbor(latitude, longitude, node.getLeft(), level, min_distance);
        } else {
            nearestNeighbor(latitude, longitude, node.getRight(), level, min_distance);
        }
        return null;
    }
}
