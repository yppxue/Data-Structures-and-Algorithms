import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V>{

    private double lf;
    private int i_size;
    private LinkedList<Entry<K, V>>[] buckets;
    private int count;
    private HashSet<K> keySet = new HashSet<>();

    public MyHashMap(){
        lf = 0.75;
        i_size = 16;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[i_size];
    }
    public MyHashMap(int initialSize){
        i_size = initialSize;
        lf = 0.75;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[i_size];
    }
    public MyHashMap(int initialSize, double loadFactor){
        i_size = initialSize;
        lf = loadFactor;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[i_size];
    }

    private class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V val){
            this.key = key;
            this.value = val;
        }

        public K getKey() {
            return key;
        }

        public V getValue(){
            return value;
        }

    }

    private Entry<K,V> findEntry(K key, LinkedList<Entry<K, V>> bucket){
        if (bucket == null) {return null;}
        for (Entry<K, V> entry:bucket){
            if (entry.getKey().equals(key)){
                return entry;
            }
        }
        return null;
    }

    private LinkedList<Entry<K, V>> getEntryList(int index){
        if (buckets[index] == null){
            buckets[index] = new LinkedList<>();
        }
        return buckets[index];
    }


    @Override
    public void clear() {
        count = 0;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[i_size];
    }

    @Override
    public boolean containsKey(K key) {
        return get(key)!= null;
    }

    private int hash(K key){
        int hashCode = key.hashCode();
        return Math.floorMod(hashCode, i_size);
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        Entry<K,V> entry = findEntry(key, buckets[index]);
        if (entry == null) {return null;}
        return entry.getValue();
    }

    @Override
    public int size() {
        return count;
    }

    private void resize(){
        int newSize = i_size * 2;
        MyHashMap<K, V> newMap = new MyHashMap<>(newSize, lf);
        for (K key : keySet){
            V value = this.get(key);
            newMap.put(key,value);
        }
        this.buckets = newMap.buckets;
        this.i_size = newSize;
    }

    @Override
    public void put(K key, V value) {
        keySet.add(key);
        int index = hash(key);
        Entry<K, V> newEntry = new Entry<>(key, value);
        Entry<K,V> entry =findEntry(key, buckets[index]);
        if (entry == null) {
            count++;
            this.getEntryList(index).add(newEntry);
        }
        else{
            entry.value = value;
        }
        if (count/i_size >= lf){
            resize();
        }
    }

    @Override
    public Set keySet() {
        return keySet;
    }

    @Override
    public Object remove(Object key) {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public Object remove(Object key, Object value) {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }
}
