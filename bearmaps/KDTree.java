package bearmaps.proj2ab;
import java.util.List;

public class KDTree implements PointSet {
    private static final boolean HORIZONTAL = false;
    //private static final boolean vertical = false;
    private Node root;

    private class Node {
        private Point p;
        private Node left; //also refers to down child
        private Node right; //also refers to up child
        private boolean orientation;

        private Node(Point giveNp, boolean p) {
            this.p = giveNp;
            orientation = p;
        }
    }

    public KDTree(List<Point> points) {
        for (Point p: points) {
            root = add(p, root, HORIZONTAL);
        }
    }

    private Node add(Point p, Node n, boolean orientation) {
        if (n == null) {
            return new Node(p, orientation);
        } else if (p.equals(n.p)) {
            return n;
        }
        int cmp = compareP(p, n.p, orientation);
        if (cmp < 0) {
            n.left = add(p, n.left, !orientation);
        } else if (cmp >= 0) {
            n.right = add(p, n.right, !orientation);
        }
        return n;
    }

    private int compareP(Point a, Point b, boolean orientation) {
        if (orientation == HORIZONTAL) {
            return Double.compare(a.getX(), b.getX());
        } else {
            return Double.compare(a.getY(), b.getY());
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point spot = new Point(x, y);
        return findNearest(root, spot, root, false).p;
    }

    private Node findNearest(Node n, Point goal, Node best, boolean orientation) {
        Node goodSide;
        Node badSide;
        boolean checkPrunebad = false;
        double dist = java.lang.Math.sqrt(Point.distance(goal, best.p));
        if (n == null) {
            return best;
        }
        if (Point.distance(n.p, goal) < Point.distance(best.p, goal)) {
            best = n;
        }
        if (compareP(goal, n.p, orientation) < 0) {
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide = n.left;
        }
        if (orientation) {
            if (dist > java.lang.Math.abs(goal.getY() - n.p.getY())) {
                checkPrunebad = true;
            }
        } else {
            if (dist > java.lang.Math.abs(goal.getX() - n.p.getX())) {
                checkPrunebad = true;
            }
        }
        best = findNearest(goodSide, goal, best, !orientation);
        if (checkPrunebad) {
            best = findNearest(badSide, goal, best, !orientation);
        }
        return best;
    }
}
