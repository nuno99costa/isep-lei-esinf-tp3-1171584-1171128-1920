package treebase;

public class LatitudeLongitude2DTree {
    KDT tree = null;

    public LatitudeLongitude2DTree() {
        tree = new KDT(2);
    }

    public KDT getTree() {
        return tree;
    }
}
