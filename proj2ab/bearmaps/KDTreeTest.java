package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
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
        randomTest(N, M);
    }

    public void randomTest(int nPoints, int nQueries){
        List<Point> points = nDoubles(nPoints);
        List<Point> compPoints = nDoubles(nQueries);
        KdTree kd = new KdTree(points);
        NaivePointSet np = new NaivePointSet(points);
        for (Point point : compPoints){
            assertEquals(kd.nearest(point.getX(), point.getY()), np.nearest(point.getX(), point.getY()));
        }
    }

    private void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }
    @Test
    public void timingTest() {
        timeKdTree();
        timeNaivePointSet();
    }

    public void timeKdTree() {
        // TODO: YOUR CODE HERE
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        for (int i = 125; i <= 50000; i = i*2){
            Ns.add(i);
            opCounts.add(i);
        }
        for (int i = 0; i < Ns.size(); i++){
            Stopwatch sw = new Stopwatch();
            List<Point> points = nDoubles(Ns.get(i));
            List<Point> compPoints = nDoubles(opCounts.get(i));
            KdTree kd = new KdTree(points);
            for (Point point : compPoints) {
                kd.nearest(point.getX(), point.getY());
            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
        }
        System.out.println("Timing table for Kd-Tree Nearest");
        printTimingTable(Ns, times, opCounts);

    }

    public void timeNaivePointSet() {
        // TODO: YOUR CODE HERE
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        for (int i = 125; i <= 50000; i = i*2){
            Ns.add(i);
            opCounts.add(i);
        }
        for (int i = 0; i < Ns.size(); i++){
            Stopwatch sw = new Stopwatch();
            List<Point> points = nDoubles(Ns.get(i));
            List<Point> compPoints = nDoubles(opCounts.get(i));
            NaivePointSet np = new NaivePointSet(points);
            for (Point point : compPoints) {
                np.nearest(point.getX(), point.getY());
            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
        }
        System.out.println("Timing table for Naive Nearest");
        printTimingTable(Ns, times, opCounts);

    }


}
