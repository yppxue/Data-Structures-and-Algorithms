package bearmaps.proj2d.trie;

import java.util.*;

public class MyTrieSet implements TrieSet61B{
    private Node root = new Node();

    private class Node{
        char ch;
        boolean isKey;
        Map<Character, Node> map;

        public Node(char c, boolean b){
            ch = c;
            isKey = b;
            map = new HashMap<>();
        }

        public Node(){
            isKey = false;
            map = new HashMap<>();
        }
    }


    @Override
    public void clear() {
        root.map.clear();
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1){
            throw new IllegalArgumentException("Empty String");
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++){
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)){
                return false;
            }
            curr = curr.map.get(c);
        }
        if (curr.isKey) { return true;}
        else {return false;}

    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++){
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)){
                return null;
            }
            curr = curr.map.get(c);
        }
        List<String> strs = new ArrayList<>();
        for (Map.Entry<Character, Node> entry : curr.map.entrySet()) {
            char c = entry.getKey();
            strs = colHelp(prefix + c, strs, curr.map.get(c));
        }
        return strs;
    }

    private List<String> colHelp(String s, List<String> x, Node n){
        if (n.isKey){
            x.add(s);
        }
        for (Map.Entry<Character, Node> entry : n.map.entrySet()){
            char c = entry.getKey();
            x = colHelp(s + c, x, n.map.get(c));
        }
        return x;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
    }
}
