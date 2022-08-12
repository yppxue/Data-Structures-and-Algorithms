package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    public KdTree buildLectureTree() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KdTree kd = new KdTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        return kd;
    }
    @Test
    public void testNearest(){
        KdTree kd = buildLectureTree();
        Point actual = kd.nearest(0,7);
        Point expected = new Point(1,5);
        assertEquals(actual, expected);
    }
    private Point randomDoubles(){
        Random rand = new Random();
        double x = rand.nextDouble();
        double y = rand.nextDouble();
        return new Point(x, y);
    }
    private List<Point> nDoubles(int N){
        List<Point> points = new ArrayList<>(N);
        for (int i = 1; i < N; i++){
            Point point = randomDoubles();
            points.add(point);
        }
        return points;
    }
    @Test
    public void randomTest(){
        int N = 1000;
        int M = 100;
        List<Point> points = new ArrayList<>(N);
        List<Point> compPoints = new ArrayList<>(M);
        points = nDoubles(N);
        compPoints = nDoubles(M);
        KdTree kd = new KdTree(points);
        NaivePointSet np = new NaivePointSet(points);
        for (Point point : compPoints){
            assertEquals(kd.nearest(point.getX(), point.getY()), np.nearest(point.getX(), point.getY()));
        }
    }


}
