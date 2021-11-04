public class SSListDeque<GenType> {
    private GenNode sentinel;
    private int size;
    private class GenNode {
        public GenType item;
        public GenNode next;

        public GenNode (GenType i, GenNode n){
            item = i;
            next = n;
        }
    }
    /** create an empty list */
    public SSListDeque() {
        sentinel = new GenNode(null, null);
        size = 0;
    }

    public SSListDeque(GenType item) {
        sentinel.next = new GenNode(item, null);
        size = 1;
    }


    public void addFirst(GenType item){
        sentinel.next = new GenNode(item, sentinel.next);
        size = size + 1;
    }

    public void addLast(GenType item){
        GenNode p = sentinel;
        while (p.next != null){
            p = p.next;
        }
        p.next = new GenNode(item, null);
        size = size + 1;

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
        while (p.next != null){
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
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
        size = size - 1;
        return p.item;

    }

    public GenType removeLast(){
        GenNode p = sentinel;
        if (p == null){
            return null;
        }
        while (p.next.next != null){
            p = p.next;
        }
        GenType item = p.next.item;
        p.next = null;
        size = size - 1;
        return item;
    }

    public GenType get(int index){
        GenNode p = sentinel;
        while (index != 0){
            if (p.next == null){
                return null;
            }
            index -= 1;
            p = p.next;
        }
        return p.next.item;

    }
}
