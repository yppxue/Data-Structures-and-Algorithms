public class LinkedListDeque<GenType> {
    private GenNode sentinel;
    private int size;
    private class GenNode {
        public GenType item;
        public GenNode next;
        public GenNode prev;

        public GenNode (GenType i, GenNode p, GenNode n){
            item = i;
            next = n;
            prev = p;
        }
    }
    /** create an empty list */
    public LinkedListDeque () {
        sentinel = new GenNode(null, sentinel, sentinel);
        size = 0;
    }

    public LinkedListDeque (GenType item) {
        sentinel.next = new GenNode(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }


    public void addFirst(GenType item){
        sentinel.next = new GenNode(item, sentinel, sentinel.next);
        sentinel.prev = sentinel.next;
        size = size + 1;
    }

    public void addLast(GenType item){
        sentinel.prev.next = new GenNode(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;

    }

    public int size (){
        return size;
    }

    public boolean isEmpty (){
        if (size == 0){
            return true;
        }
        return false;
    }

    public void printDeque(){
        GenNode p = sentinel;
        p = p.next;
        int i = size;
        while (i != 1){
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
            i -= 1;
        }
        System.out.println(p.item);
    }

    public GenType removeFirst(){
        GenNode p = sentinel;
        p = p.next;
        if (p == null){
            return null;
        }
        sentinel.next = p.next;
        sentinel.next.prev = sentinel;
        size = size - 1;
        return p.item;

    }

    public GenType removeLast(){
        GenNode p = sentinel;
        if (p == null){
            return null;
        }
        GenType item = p.prev.item;
        p.prev.prev.next = sentinel;
        sentinel.prev = p.prev.prev;
        size = size - 1;
        return item;
    }

    public GenType get(int index){
        GenNode p = sentinel;
        while (index != 0){
            if (p.next == sentinel){
                return null;
            }
            index -= 1;
            p = p.next;
        }
        return p.next.item;
    }

    public GenType getRecursive (int index){
        GenNode p = sentinel;
        p = recurHelper(index, p);
        return p.next.item;
    }

    public  GenNode recurHelper (int index, GenNode p){
        if (index == 0 || p.next == sentinel){
            return p;
        }
        return recurHelper(index - 1, p.next);
    }
}
