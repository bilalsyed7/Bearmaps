package bearmaps;
import org.junit.Test;
import java.util.List;
import java.util.Random;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import java.util.Random;

public class KDTreeTest {
    private static Random r = new Random(500);

    public static KDTree insertTester() {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(4, 5);
        Point p7 = new Point(1, 5);
        Point p8 = new Point(4, 4);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7, p8));
        return kd;
    }

    @Test
    //@Source Professor Hug
    public void testNearest() {
        KDTree kd = insertTester();
        Point actual = kd.nearest(0, 7);
        Point expected = new Point(1, 5);
        assertEquals(expected, actual);
    }

    private Point randomPoint() {
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x, y);
    }

    private List<Point> randomPoints(int N) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            points.add(randomPoint());
        }
        return points;
    }


    private void testWithNPointsandQQueries(int pointcount, int querycount) {
        List<Point> points = randomPoints(pointcount);
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kd = new KDTree(points);

        List<Point> queries = randomPoints(querycount);
        for (Point p : queries) {
            Point expected = nps.nearest(p.getX(), p.getY());
            Point actual = kd.nearest(p.getX(), p.getY());
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testwith1000pointsAnd200queries() {
        int pointCount = 1000;
        int queryCount = 200;
        testWithNPointsandQQueries(pointCount, queryCount);
    }

    @Test
    public void testWith10000PointsAnd2000queries() {
        int pointCount = 10000;
        int queryCount = 2000;
        testWithNPointsandQQueries(pointCount, queryCount);

    }

    private void timingTest(int pointcount, int querycount) {
        List<Point> points = randomPoints(pointcount);
        KDTree kd = new KDTree(points);

        Stopwatch sw = new Stopwatch();
        List<Point> queries = randomPoints(querycount);
        for (Point p : queries) {
            Point actual = kd.nearest(p.getX(), p.getY());
        }
        System.out.println("Time elapsed for " + querycount + " queries on " + pointcount + " points: " + sw.elapsedTime());
    }

    @Test
    public void timingTest() {
        List<Integer> pointcounts = List.of(1000, 10000, 100000, 1000000);
        for (int N: pointcounts){
            timingTest(N, 10000);
        }

    }

    @Test
    public void NaiveVsKDTree() {
        List<Point> points = randomPoints(100000);
        KDTree kd = new KDTree(points);
        NaivePointSet nps = new NaivePointSet(points);

        List<Point> queryPoints = randomPoints(10000);
        Stopwatch sw = new Stopwatch();

        for (Point p: queryPoints) {
            nps.nearest(p.getX(), p.getY());
        }
        double time = sw.elapsedTime();
    System.out.println("Naive 10000 queries on 100000 points: " + time );

    Stopwatch s2 = new Stopwatch();
    for (Point p: queryPoints) {
        kd.nearest(p.getX(), p.getY());
    }
    double time2 = s2.elapsedTime();
    System.out.println("KD 10000 queries on 100000 points: " + time2 );
    }
}
