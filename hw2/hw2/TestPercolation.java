package hw2;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolation {

    @Test
    public void test1(){
        Percolation percolation = new Percolation(5);
        percolation.open(3,4);
        assertTrue(percolation.isOpen(3,4));
        percolation.open(2,4);
        percolation.open(2,2);
        percolation.open(2,3);
        percolation.open(0,2);
        percolation.open(1,2);
        assertTrue(percolation.isOpen(1,2));
        //assertTrue(percolation.isFull(2,2));
        return;
    }
}
