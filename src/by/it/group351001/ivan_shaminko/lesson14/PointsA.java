package by.it.group351001.ivan_shaminko.lesson14;

import java.util.*;

public class PointsA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int distance = scanner.nextInt();
        int counter = scanner.nextInt();

        DisJointSet<Point> dsu = new DisJointSet<Point>();

        for (int i = 0; i < counter; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            Point point = new Point(x, y, z);
            dsu.makeSet(point);

            for (Point currentPoint : dsu) {
                if (point.distanceTo(currentPoint) < distance) {
                    dsu.union(point, currentPoint);
                }
            }
        }

        List<Integer> clusterSizes = new ArrayList<>();
        HashSet<Point> set = new HashSet<>();
        for (Point existingPoint : dsu) {
            Point root = dsu.findSet(existingPoint);
            if (set.contains(root))
                continue;

            set.add(root);
            int size = dsu.getClusterSize(root);
            clusterSizes.add(size);
        }

        Collections.sort(clusterSizes);
        Collections.reverse(clusterSizes);

        for (int size : clusterSizes) {
            System.out.print(size + " ");
        }
    }

    static class Point {
        int x, y, z;

        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        double distanceTo(Point other) {
            return Math.hypot(Math.hypot(x - other.x, y - other.y), z - other.z);
        }
    }
}