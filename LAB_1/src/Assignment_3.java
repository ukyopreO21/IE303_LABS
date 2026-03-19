import java.util.*;

public class Assignment_3 {
    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static long crossProduct(Point a, Point b, Point c) {
        return (long) (b.x - a.x) * (c.y - b.y) - (long) (b.y - a.y) * (c.x - b.x);
    }

    public static List<Point> getConvexHull(List<Point> points) {
        int n = points.size();
        if (n <= 2) return new ArrayList<>(points);

        points.sort(Comparator.comparingInt((Point p) -> p.x).thenComparingInt(p -> p.y));

        List<Point> hull = new ArrayList<>();
        for (Point p : points) {
            while (hull.size() >= 2 && crossProduct(hull.get(hull.size() - 2), hull.getLast(), p) <= 0) {
                hull.removeLast();
            }
            hull.add(p);
        }

        int lowerHullSize = hull.size();
        for (int i = n - 2; i >= 0; i--) {
            Point p = points.get(i);
            while (hull.size() > lowerHullSize && crossProduct(hull.get(hull.size() - 2), hull.getLast(), p) <= 0) {
                hull.removeLast();
            }
            hull.add(p);
        }

        hull.removeLast();

        return hull;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Point> points = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            points.add(new Point(sc.nextInt(), sc.nextInt()));
        }

        sc.close();

        for (Point p : getConvexHull(points)) {
            System.out.println(p.x + " " + p.y);
        }
    }
}
