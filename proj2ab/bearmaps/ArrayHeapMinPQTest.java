package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    ArrayHeapMinPQ<Double> minPQDouble = new ArrayHeapMinPQ<>();
    NaiveMinPQ<Double> naiveMinPQDouble = new NaiveMinPQ<>();
    @Test
    public void testContainMethod (){
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(1, 1);
        minPQ.add(5, 5);
        minPQ.add(6, 6);
        assertTrue(minPQ.contains(1));
        assertTrue(minPQ.contains(5));
        assertTrue(minPQ.contains(6));
        minPQ.removeSmallest();
        assertFalse(minPQ.contains(1));
        minPQ.removeSmallest();
        assertFalse(minPQ.contains(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {

        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(1, 1);
        minPQ.add(1, 5);

    }

    @Test
    public void testGetsmallest() {

        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(1, 1);
        minPQ.add(2, 5);
        minPQ.add(6, 6);
        assertTrue(minPQ.getSmallest().equals(1));

    }

    @Test
    public void testRandomItems(){
        int N = 50000;
        randomlizedMinPQ(N);
        for (int i = N; i < minPQDouble.size(); i++) {
            minPQDouble.removeSmallest();
            naiveMinPQDouble.removeSmallest();
            assertTrue(minPQDouble.getSmallest().equals(naiveMinPQDouble.getSmallest()));
        }
    }

    @Test
    public void TestChangePrivority() {
        ArrayHeapMinPQ<String> arrayHeapMinPQ = new ArrayHeapMinPQ<>();

        arrayHeapMinPQ.add("ddd",1.5);
        arrayHeapMinPQ.add("eee",1.6);
        arrayHeapMinPQ.add("fff",1.7);
        arrayHeapMinPQ.add("ggg",1.8);
        arrayHeapMinPQ.changePriority("eee",1.0);
        assertEquals("eee",arrayHeapMinPQ.getSmallest());
        arrayHeapMinPQ.removeSmallest();
        assertEquals("ddd",arrayHeapMinPQ.getSmallest());
        arrayHeapMinPQ.add("aaa",0.9);
        assertEquals("aaa",arrayHeapMinPQ.getSmallest());
    }

    @Test
    public void TestContain() {
        ArrayHeapMinPQ<String> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        arrayHeapMinPQ.add("ddd",1.5);
        arrayHeapMinPQ.add("eee",1.6);
        arrayHeapMinPQ.add("fff",1.7);
        arrayHeapMinPQ.add("ggg",1.8);
        assertTrue(arrayHeapMinPQ.contains("ddd"));
        arrayHeapMinPQ.removeSmallest();
        assertFalse(arrayHeapMinPQ.contains("ddd"));
    }

    private void randomlizedMinPQ(int N){
        Random rand = new Random();
        for (int i = 1; i <= N; i ++){
            double item = rand.nextDouble();
            double priority = rand.nextDouble();
            minPQDouble.add(item, priority);
            naiveMinPQDouble.add(item, priority);
        }
    }

    private void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (ms)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i) * 1e6;
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    @Test
    public void timeNaiveMinPQ() {
        // TODO: YOUR CODE HERE
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        for (int i = 125; i <= 1000000; i = i*2){
            Ns.add(i);
            opCounts.add(i);
        }
        for (int i = 0; i < Ns.size(); i++){
            randomlizedMinPQ(Ns.get(i));
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < opCounts.size(); j++) {
                Random rand = new Random();
                naiveMinPQDouble.contains(rand.nextDouble());
            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
        }
        System.out.println("Timing table for Naive Contain");
        printTimingTable(Ns, times, opCounts);

    }

    @Test
    public void timeMinHeapPQ() {
        // TODO: YOUR CODE HERE
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        for (int i = 125; i <= 1000000; i = i*2){
            Ns.add(i);
            opCounts.add(i);
        }
        for (int i = 0; i < Ns.size(); i++){
            randomlizedMinPQ(Ns.get(i));
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < opCounts.size(); j++) {
                Random rand = new Random();
                minPQDouble.contains(rand.nextDouble());
            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
        }
        System.out.println("Timing table for Naive Contain");
        printTimingTable(Ns, times, opCounts);

    }

    @Test
    public void TestTime() {
        long start1 = System.currentTimeMillis();
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 20000; i += 1) {
            arrayHeapMinPQ.add(i,10000-i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("After 20000 insert, ArrayHeapMinPQ time spends: " + (end1 - start1) + "ms.");

        long start2 = System.currentTimeMillis();
        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();
        for (int i = 0; i < 20000; i += 1) {
            naiveMinPQ.add(i,10000-i);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("After 20000 insert, NaiveMinPQ time spends: " + (end2 - start2) + "ms.");


        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 20000; i += 1) {
            arrayHeapMinPQ.contains(i);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("After 20000 contains, ArrayHeapMinPQ time spends: " + (end3 - start3) + "ms.");

        long start4 = System.currentTimeMillis();
        for (int i = 0; i < 20000; i += 1) {
            naiveMinPQ.contains(i);
        }
        long end4 = System.currentTimeMillis();
        System.out.println("After 20000 contians, NaiveMinPQ time spends: " + (end4 - start4) + "ms.");


        long start5 = System.currentTimeMillis();
        for (int i = 0; i < 20000; i += 1) {
            arrayHeapMinPQ.changePriority(i,i+20000);
        }
        long end5 = System.currentTimeMillis();
        System.out.println("After 20000 changePriority, ArrayHeapMinPQ time spends: " + (end5 - start5) + "ms.");

        long start6 = System.currentTimeMillis();
        for (int i = 0; i < 20000; i += 1) {
            naiveMinPQ.changePriority(i,i+20000);
        }
        long end6 = System.currentTimeMillis();
        System.out.println("After 20000 changePriority, NaiveMinPQ time spends: " + (end6 - start6) + "ms.");


        long start7 = System.currentTimeMillis();
        for (int i = 0; i < 20000; i += 1) {
            arrayHeapMinPQ.removeSmallest();
        }
        long end7 = System.currentTimeMillis();
        System.out.println("After 20000 removeSmallest, ArrayHeapMinPQ time spends: " + (end7 - start7) + "ms.");

        long start8 = System.currentTimeMillis();
        for (int i = 0; i < 20000; i += 1) {
            naiveMinPQ.removeSmallest();
        }
        long end8 = System.currentTimeMillis();
        System.out.println("After 20000 removeSmallest, NaiveMinPQ time spends: " + (end8 - start8) + "ms.");
    }



}
