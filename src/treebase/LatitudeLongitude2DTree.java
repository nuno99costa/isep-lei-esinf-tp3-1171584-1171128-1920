package treebase;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class LatitudeLongitude2DTree {
    Country nearestNeighbor;
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
        double min = Double.MAX_VALUE;
        nearestLeaftoPoint(latitude, longitude, tree.root, 0, new LinkedList(), min);
        return nearestNeighbor;
    }

    private void nearestLeaftoPoint(double latitude, double longitude, KDT.Node node, int node_level, Queue q, double minDist) {
        double distRight = 0;
        double distLeft = 0;
        if (distanceBetweenCountryPoint((Country) node.getElement(), latitude, longitude) < minDist) {
            minDist = distanceBetweenCountryPoint((Country) node.getElement(), latitude, longitude);
            q.add(node);
            nearestNeighbor = (Country) node.getElement();
        }
        if (node.isLeaf()) {
            boundingBoxBacktrack(latitude, longitude, minDist, node_level, q);
        } else if (node.getRight() == null) {
            q.add(node);
            nearestLeaftoPoint(latitude, longitude, node.getLeft(), node_level + 1, q, minDist);
        } else if (node.getLeft() == null) {
            q.add(node);
            nearestLeaftoPoint(latitude, longitude, node.getRight(), node_level + 1, q, minDist);
        } else {
            if (node_level > 1)
                node_level -= 2;
            if (node_level == 0) {
                distRight = ((Country) node.getRight().getElement()).getLatitude() - latitude;
                distLeft = ((Country) node.getLeft().getElement()).getLatitude() - latitude;
            }
            if (node_level == 1) {
                distRight = ((Country) node.getRight().getElement()).getLongitude() - longitude;
                distLeft = ((Country) node.getLeft().getElement()).getLongitude() - longitude;
            }
            if (Math.abs(distRight) > Math.abs(distLeft)) {
                q.add(node);
                nearestLeaftoPoint(latitude, longitude, node.getLeft(), node_level + 1, q, minDist);
            } else {
                q.add(node);
                nearestLeaftoPoint(latitude, longitude, node.getRight(), node_level + 1, q, minDist);
            }
        }
    }

    private void boundingBoxBacktrack(double latitude, double longitude, double minDist, int node_level, Queue q) {
        KDT.Node current = (KDT.Node) q.poll();
        node_level++;
        if (node_level > 1)
            node_level -= 2;
        if (current.getRight() != null && current.getLeft() != null) {
            if (q.contains(current.getRight()) && current.getLeft().getBox().getDistanceToBox(latitude, longitude) < minDist) {
                nearestLeaftoPoint(latitude, longitude, current.getLeft(), node_level, q, minDist);
            } else if (q.contains(current.getLeft()) && current.getRight().getBox().getDistanceToBox(latitude, longitude) < minDist) {
                nearestLeaftoPoint(latitude, longitude, current.getRight(), node_level, q, minDist);
            }
        }
        if (!q.isEmpty())
            boundingBoxBacktrack(latitude, longitude, minDist, node_level, q);
    }

    private double distanceBetweenCountryPoint(Country element, double latitude, double longitude) {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        double lon1 = Math.toRadians(element.getLongitude());
        double lon2 = Math.toRadians(longitude);
        double lat1 = Math.toRadians(element.getLatitude());
        double lat2 = Math.toRadians(latitude);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;

        // calculate the result
        return (c * r);
    }
}
