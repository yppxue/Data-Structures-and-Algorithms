package bearmaps;

import java.nio.channels.Pipe;
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
        public double shortestDis(Point o) {
            if (orientation == horizontal) {
                return Math.pow(o.getX() - p.getX(), 2);
            } else {
                return Math.pow(o.getY() - p.getY(), 2);
            }
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
        int cmp = compNode(newNode, root);
        if (cmp < 0){
            root.leftChild = add(newNode, root.leftChild, !root.orientation);
        }
        else{
            root.rightChild = add(newNode, root.rightChild, !root.orientation);
        }
        return root;
    }

    private int compNode(Node newNode, Node targetNode){
        if (targetNode.orientation == horizontal) {
            //compare horizontal
            // newNode < targetNode;
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
        Node best = nearestHelper(root, goal, root);
        return best.p;
    }

    private Node nearestHelper (Node n, Point goal, Node best){
        if (n == null){
            return best;
        }
        double n_distance = Point.distance(n.p, goal);
        double best_distance = Point.distance(best.p, goal);
        if (n_distance < best_distance){
            best = n;
        }
        Node goodSide, badSide;
        Node target = new Node(goal);
        int cmp = compNode(target, n);
        if (cmp < 0){
            goodSide = n.leftChild;
            badSide = n.rightChild;
        }else{
            goodSide = n.rightChild;
            badSide = n.leftChild;
        }
        best = nearestHelper(goodSide, goal, best);
        if (Double.compare(n.shortestDis(goal), Point.distance(best.p, goal)) < 0){
            best = nearestHelper(badSide, goal, best);
        }
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
