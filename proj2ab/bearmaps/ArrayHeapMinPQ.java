package bearmaps;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private ArrayList<PriorityNode> pq;                         //array from 1 to n.
    private TreeMap<T, Integer> itemMap;
    private int n;                          //size of heap

    public ArrayHeapMinPQ(){
        pq = new ArrayList<>();
        itemMap = new TreeMap<>();
        PriorityNode node = new PriorityNode(null, 0);
        pq.add(node);
        n = 0;
    }

    public ArrayHeapMinPQ(T[] items){
        n = items.length;
        for (int i=0; i < n; i++){
            PriorityNode node = new PriorityNode(items[i], i + 1);
            pq.add(node);
        }
        //need to check inserted array maintain heap property
    }


    @Override
    public void add(T item, double priority) {
        if (itemMap.containsKey(item)){
            throw new IllegalArgumentException("Item exists");
        }
        pq.add(new PriorityNode(item, priority));
        n++;
        itemMap.put(item, n);
        swim(n);
    }

    @Override
    public boolean contains(T item) {
        return itemMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return pq.get(1).getItem();
    }

    @Override
    public T removeSmallest() {
        //swap the last item in the heap into the root.
        T removedItem = getSmallest();
        exchange(1, n);
        pq.remove(n);
        itemMap.remove(removedItem);
        n = n - 1;
        sink(1);
        return removedItem;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void changePriority(T item, double priority) {
        int k = itemMap.get(item);
        PriorityNode targetNode = pq.get(k);
        double oldPriority = targetNode.getPriority();
        targetNode.setPriority(priority);
        if (Double.compare(priority, oldPriority) < 0){
            swim(k);
        }
        if (Double.compare(priority, oldPriority) > 0){
            sink(k);
        }
    }


    //helper function:
    private void swim(int k){
        //exchange with parent node if needed
        while (k > 1 && (compare(k/2, k) > 0)){
            exchange(k/2, k);
            k = k /2;
        }
    }

    private void sink(int k){
        //exchange with parent node if needed
        while (2*k <= n){
            int j = 2*k;
            if (j < n && compare(j, j+1) > 0){j = j+1;}
            if (j < n && compare(j, j+1) == 0){
                Random rand = new Random();
                int m = rand.nextDouble() >= 0.5 ? 1 : 0;
                j = j + m;
            }
            if (compare(k, j) < 0){break;}
            exchange(k, j);
            k = j;
        }
    }

    private int compare(int parent, int child){
        PriorityNode parentNode = pq.get(parent);
        PriorityNode childNode = pq.get(child);
        int cmp = parentNode.compareTo(childNode);
        return (cmp);
    }

    private void exchange(int parent, int child) {
        PriorityNode parentNode = pq.get(parent);
        PriorityNode childNode = pq.get(child);
        T parentItem = parentNode.getItem();
        T childItem = childNode.getItem();
        pq.set(parent, childNode);
        itemMap.replace(childItem, parent);
        pq.set(child, parentNode);
        itemMap.replace(parentItem, child);
    }

    //priority node:

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

        public static void main(String[] args) {
        //ignore duplicate items;
        ArrayHeapMinPQ<String> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add("A_1", 1);
        minPQ.add("A_5", 5);
        minPQ.add("B_1", 1);
        minPQ.add("A_6", 6);
        minPQ.add("B_5", 5);
        minPQ.add("B_6", 6);
        minPQ.add("A_3", 3);
        minPQ.add("A_7", 7);
        minPQ.add("B_7", 7);
        minPQ.add("A_8", 8);
        minPQ.add("B_3", 3);
        minPQ.add("C_5", 5);
        //minPQ.removeSmallest();
        //minPQ.removeSmallest();
        minPQ.changePriority("C_5", 0.5);
        minPQ.changePriority("C_5", 5);
        String[] items = new String[20];
        //tranfer arraylist to integer array for printing
        for (int i = 1; i < minPQ.pq.size(); i++){
            items[i] = minPQ.pq.get(i).getItem();
        }
        PrintHeapDemo.printFancyHeapDrawing(items);
    }




}
