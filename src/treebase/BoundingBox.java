package treebase;

public class BoundingBox<E> {
    double maxX;
    double maxY;
    double minX;
    double minY;


    public BoundingBox(double maxX, double maxY, double minX, double minY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.minX = minX;
        this.minY = minY;
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
}
