package bearmaps;

import java.util.List;

public class KdTree implements PointSet{
    private static final boolean horizontal = false;
    private static final boolean vertical = true;

    private Node root;

    public class Node {
        private Point p;
        private boolean orientation;
        private Node leftChild;
        private Node rightChild;
        public Node (Point point){
            p = point;
        }
    }

    public KdTree(List<Point> points){
        for (Point point : points){
            Node newNode = new Node(point);
            root = add(newNode, root, horizontal);
        }
    }

    private Node add(Node newNode, Node root, boolean orientation){
        if (root == null){
            newNode.orientation = orientation;
            return newNode;
        }
        if (newNode.p.equals(root.p)){
            return root;
        }
        int cmp = compNode(root, newNode);
        if (cmp < 0){
            root.leftChild = add(newNode, root.leftChild, !root.orientation);
        }
        else if (cmp >= 0){
            root.rightChild = add(newNode, root.rightChild, !root.orientation);
        }
        return root;
    }

    private int compNode(Node targetNode, Node newNode){
        if (targetNode.orientation == horizontal) {
            //compare horizontal
            // < 0 newNode < targetNode;
            return Double.compare(newNode.p.getX(), targetNode.p.getX());
        }
        else{
            return Double.compare(newNode.p.getY(), targetNode.p.getY());
        }
    }

    @Override
    public Point nearest(double x, double y) {
        //initialize
        Point goal = new Point(x, y);
        Node best = nearestHelper(root, goal, null);
        return best.p;
    }

    private Node nearestHelper (Node n, Point goal, Node best){
        if (n == null){
            return best;
        }
        if (best == null){
            best = root;
        }
        double n_distance = Point.distance(n.p, goal);
        double best_distance = Point.distance(best.p, goal);
        if (n_distance < best_distance){
            best = n;
        }
        best = nearestHelper(n.leftChild, goal, best);
        best = nearestHelper(n.rightChild, goal, best);
        return best;
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KdTree kd = new KdTree(List.of(p1, p2, p3, p4, p5, p6, p7));
    }
}
