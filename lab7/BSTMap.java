import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    public BSTMap() {
    }

    private class Node{
        private K key;
        private V val;
        private Node left, right;
        public Node(K key, V val){
            this.key = key;
            this.val = val;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node r, K key){
        if (r == null){
            return null;
        }
        int cmp = key.compareTo(r.key);
        if (cmp == 0){
            return r.val;
        }
        else if (cmp < 0){
            return get(r.left, key);
        }
        else{
            return get(r.right, key);
        }

    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node r){
        if (r == null){
            return 0;
        }
        return 1 + size(r.left) + size(r.right);
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
        return;
    }

    private Node put(Node r, K key, V value) {
        if (r == null){
            return new Node(key,value);
        }
        int cmp = key.compareTo(r.key);
        if (cmp < 0){
            r.left = put(r.left, key, value);
        }
        else if (cmp > 0){
            r.right = put(r.right, key, value);
        }
        else {
            r.val = value;
        }
        return r;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Unsupported Operation");
    }
}
