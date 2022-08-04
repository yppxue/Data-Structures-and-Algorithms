package hw2;
import edu.princeton.cs.introcs.StdRandom;

import java.util.stream.IntStream;

public class PercolationStats {
    int[] x;
    public PercolationStats(int N, int T, PercolationFactory pf){
        x = new int[T];
        for (int i=0; i<T; i++){
            Percolation perc = pf.make(N);
            while(!perc.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                perc.open(row, col);
            }
            x[i] = perc.numberOfOpenSites();
        }
    }   // perform T independent experiments on an N-by-N grid
    public double mean(){
        int total = 0;
        for (int i=0; i<x.length; i++){
            total += x[i];
        }
        double mean = total/x.length;
        return mean;
    }                                          // sample mean of percolation threshold
//    public double stddev(){
//        return null;
//    }                                         // sample standard deviation of percolation threshold
//    public double confidenceLow(){
//        return null;
//    }                                  // low endpoint of 95% confidence interval
//    public double confidenceHigh(){
//        return null;
//    }                                 // high endpoint of 95% confidence interval
}
