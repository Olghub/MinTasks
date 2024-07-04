package org.example.task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Circle circle = getCircle(args[0]); // src/main/java/org/example/task2/circle.txt
        if (circle == null) {
            return;
        }

        List<Point> points = getPoints(args[1]); //src/main/java/org/example/task2/points.txt
        if (points == null) {
            return;
        }

        List<Integer> positionList = createPositions(circle, points);
        positionList.forEach(System.out::println);
    }

    private static Circle getCircle(String filePath) {
        try (BufferedReader circleFile = new BufferedReader(new FileReader(filePath))) {
            String[] line = circleFile.readLine().split(" ");

            Double x = Double.parseDouble(line[0]);
            Double y = Double.parseDouble(line[1]);
            Double r2 = Math.pow(Double.parseDouble(circleFile.readLine()), 2);
            return new Circle(x, y, r2);
        } catch (IOException ex) {
            return null;
        }
    }

    private static List<Point> getPoints(String filePath) {
        List<Point> points = new LinkedList<>();
        try (BufferedReader pointsFile = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = pointsFile.readLine()) != null) {
                String[] coordinates = line.split(" ");
                Double x = Double.parseDouble(coordinates[0]);
                Double y = Double.parseDouble(coordinates[1]);

                points.add(new Point(x, y));
            }

            return points;
        } catch (IOException ex) {
            return null;
        }
    }

    private static List<Integer> createPositions(Circle circle, List<Point> points) {
        List<Integer> positionList = new LinkedList<>();

        Double circleX = circle.getX();
        Double circleY = circle.getY();
        Double circleR2 = circle.getR2();
        for (Point point : points) {
            double diff = Math.pow(point.getX() - circleX, 2) + Math.pow(point.getY() - circleY, 2) - circleR2;

            if (diff > 0) {
                positionList.add(2);
                continue;
            }

            if (diff == 0) {
                positionList.add(0);
                continue;
            }

            positionList.add(1);
        }

        return positionList;
    }

    public static class Circle {
        private Double x;
        private Double y;
        private Double r2;

        public Circle(Double x, Double y, Double r2) {
            this.x = x;
            this.y = y;
            this.r2 = r2;
        }

        public Double getX() {
            return x;
        }

        public void setX(Double x) {
            this.x = x;
        }

        public Double getY() {
            return y;
        }

        public void setY(Double y) {
            this.y = y;
        }

        public Double getR2() {
            return r2;
        }

        public void setR2(Double r2) {
            this.r2 = r2;
        }
    }

    public static class Point {
        private Double x;
        private Double y;

        public Point(Double x, Double y) {
            this.x = x;
            this.y = y;
        }

        public Double getX() {
            return x;
        }

        public void setX(Double x) {
            this.x = x;
        }

        public Double getY() {
            return y;
        }

        public void setY(Double y) {
            this.y = y;
        }
    }
}
