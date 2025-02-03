package com.phasmidsoftware.dsaipg.misc.randomwalk;
 
import java.util.Random;
 
public class RandomWalk {
 
    private int x = 0; // Current x-coordinate
    private int y = 0; // Current y-coordinate
    private final Random random = new Random();
 
    /**
     * Method to compute the Euclidean distance from the origin.
     *
     * @return the distance from the origin to the current position.
     */
    public double distance() {
        // Handling overflow situations: return the Euclidean distance
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
 
    /**
     * Method to move the position based on dx and dy.
     *
     * @param dx the change in the x-direction
     * @param dy the change in the y-direction
     */
    private void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
 
    /**
     * Perform a single random move (North, South, East, or West).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean(); // Choose North-South or East-West
        int step = random.nextBoolean() ? 1 : -1; // Positive or negative step
        move(ns ? step : 0, ns ? 0 : step); // Update position
    }
 
    /**
     * Perform a random walk of m steps.
     *
     * @param m the number of steps to take
     */
    private void randomWalk(int m) {
        for (int i = 0; i < m; i++) {
            randomMove();
        }
    }
 
    /**
     * Perform multiple random walk experiments and compute the mean distance.
     * @param m the number of steps in each experiment
     * @param n the number of experiments to perform
     * @return the mean distance over n experiments
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance += walk.distance();
        }
        return totalDistance / n;
    }
 
    /**
     * Main method to run experiments for various values of m and generate results.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        int[] stepsArray = {10, 50, 100, 200, 500, 1000}; // Values of m
        int experiments = 10; // n = 10 random walks
        double[] observedDistances = new double[stepsArray.length];
        double[] theoreticalDistances = new double[stepsArray.length];
 
        System.out.println("Steps\tObserved Mean Distance");
 
        for (int i = 0; i < stepsArray.length; i++) {
            int m = stepsArray[i];
            double meanDistance = randomWalkMulti(m, experiments); // Run 10 experiments for each m
            observedDistances[i] = meanDistance;
            theoreticalDistances[i] = Math.sqrt(m);
            System.out.printf("%d\t%.4f\t\t\t%n", m, meanDistance);
        }
    }
}