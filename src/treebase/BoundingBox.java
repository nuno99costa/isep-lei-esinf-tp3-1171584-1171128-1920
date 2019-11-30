package treebase;

public class BoundingBox<E> {
    double maxX;
    double maxY;
    double minX;
    double minY;


    public BoundingBox(double lat1, double long1, double lat2, double long2) {
        if (lat1 > lat2) {
            maxX = lat1;
            minX = lat2;
        } else {
            maxX = lat2;
            minX = lat1;
        }
        if (long1 > long2) {
            maxY = long1;
            minY = long2;
        } else {
            maxY = long2;
            minY = long1;
        }
    }

    public BoundingBox(E element) {
        this.maxX = ((Country) element).getLatitude();
        this.maxY = ((Country) element).getLongitude();
        this.minX = ((Country) element).getLatitude();
        this.minY = ((Country) element).getLongitude();
    }

    public void updateBox(E element) {
        if (((Country) element).getLatitude() > maxX)
            this.maxX = ((Country) element).getLatitude();
        if (((Country) element).getLongitude() > maxY)
            this.maxY = ((Country) element).getLongitude();
        if (((Country) element).getLatitude() < minX)
            this.minX = ((Country) element).getLatitude();
        if (((Country) element).getLongitude() < minY)
            this.minY = ((Country) element).getLongitude();
    }

    public double getDistanceToBox(double x, double y) {
        Double dx = Math.max(Math.max(minX - x, 0.0), x - maxX);
        Double dy = Math.max(Math.max(minY - y, 0.0), y - maxY);
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "(" + maxX + "," + maxY + "/" + minX + "," + minY + ")";
    }

    public boolean containsNode(KDT.Node node) {
        Country value = (Country) node.getElement();
        double lat = value.getLatitude();
        double lon = value.getLongitude();

        return lat >= minX && lat <= maxX && lon >= minY && lon <= maxY;
    }

    public boolean isRight(KDT.Node node, int level) {
        if (level == 0) {
            double lat = ((Country) node.getElement()).getLatitude();
            return lat < this.minX;
        } else {
            double lon = ((Country) node.getElement()).getLongitude();
            return lon < this.minY;
        }
    }

    public boolean holdsCord(KDT.Node node, int level) {
        if (level == 0) {
            double lat = ((Country) node.getElement()).getLatitude();
            return lat > this.minX && lat < this.maxX;
        } else {
            double lon = ((Country) node.getElement()).getLongitude();
            return lon > this.minY && lon < this.maxY;
        }
    }
}
