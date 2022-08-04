package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    static int grid;
    static int virTop;
    static int virBot;
    boolean[][] siteStatus;
    WeightedQuickUnionUF uf;
    int openCount;
    public Percolation(int N){
        grid = N;
        virTop = grid * grid;
        virBot = grid * grid + 1;
        uf = new WeightedQuickUnionUF(grid*grid+2);
        siteStatus =  new boolean[N][N];
        openCount = 0;
    }      // create N-by-N grid, with all sites initially blocked

    public void open(int row, int col) {
        if ((row) > grid - 1 || col > grid - 1 || row <0 || col < 0){
            throw new IllegalArgumentException("Invaid grid value");
        }
        if (isOpen(row,col)){
            return;
        }
        siteStatus[row][col] = true;
        openCount += 1;
        int currSite = xyTo1D(row,col);
        connVir(row, col);
        //connect with 4 sides
        //condition 1: left side:
        if (col - 1 > 0){
            if (isOpen(row, col-1)){
                uf.union(currSite, xyTo1D(row,col-1));
                connVir(row, col-1);
            }
        }
        //condition 2: right side:
        if (col + 1 < grid - 1){
            if (isOpen(row, col+1)){
                uf.union(currSite, xyTo1D(row,col+1));
                connVir(row, col+1);
            }
        }
        //condition 3: upper side:
        if (row - 1 > 0){
            if (isOpen(row-1, col)){
                uf.union(currSite, xyTo1D(row-1,col));
                connVir(row-1, col);
            }
        }
        //condition 4: lower side:
        if (row + 1 < grid - 1){
            if (isOpen(row+1, col)){
                uf.union(currSite, xyTo1D(row+1,col));
                connVir(row+1, col);
            }
        }
    }      // open the site (row, col) if it is not open already

    private void connVir(int row, int col) {
        if (row == 0){
            uf.union(virTop, xyTo1D(row,col));
        }
        else if (row == grid-1){
            uf.union(virBot,  xyTo1D(row,col));
        }
    }

    public boolean isOpen(int row, int col) {
        if ((row) > grid - 1 || col > grid - 1 || row <0 || col < 0){
            throw new IllegalArgumentException("Invaid grid value");
        }
        return siteStatus[row][col];
    }   // is the site (row, col) open?

    public boolean isFull(int row, int col) {
        if ((row) > grid - 1 || col > grid - 1 || row <0 || col < 0){
            throw new IllegalArgumentException("Invaid grid value");
        }
        return uf.connected(virTop,xyTo1D(row,col));
    }  // is the site (row, col) full?

    public int numberOfOpenSites() {
        return openCount;
    }           // number of open sites

    public boolean percolates() {
        return uf.connected(virTop,virBot);
    }              // does the system percolate?

    public static int xyTo1D (int row, int col){
        return (row)*(grid) + col;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        System.out.println(xyTo1D(2,3));
    }   // use for unit testing (not required, but keep this here for the autograder)

}
