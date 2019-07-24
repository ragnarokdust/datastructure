package datastruct;

/**
 * LinkedListImpl
 */
public class LinkedListImpl<E>{
    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    public LinkedListImpl(){

    }

    // create
    public void add(E elem){
        Node<E> last = this.last;
        Node<E> newNode = new Node<>(last, elem, null);
        this.last = newNode;
        
        if (last == null)
            first = newNode;
        else
            last.next = newNode;
        size++;
    }
    public void add(int index, E element) {
        if(isElementIndex(index)){
            if (index == size)
                addLast(element);
            else{
                Node<E> curr = node(index);
                Node<E> prev = curr.prev;
                Node<E> newNode = new Node<>(prev, element, curr);
                curr.prev = newNode;
                if(prev == null)
                    first = newNode;
                else
                    prev.next = newNode;
            }
        }
    }

    public void addFirst(E elem){
        Node<E> f = first;
        Node<E> newNode = new Node<>(null, elem, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    public void addLast(E elem) {
        final Node<E> last = this.last;
        final Node<E> newNode = new Node<>(last, elem, null);
        this.last = newNode;
        if (last == null)
            first = newNode;
        else
            last.next = newNode;
        size++;
    }

    // read
    
    public E get(int index) {
        if(isElementIndex(index)){
            return node(index).item;
        }
        return null;
    }
    
    
    // update
    
    public E set(int index, E element) {
        if(isElementIndex(index)){
            Node<E> elem = node(index);
            E oldVal = elem.item;
            elem.item = element;
            return oldVal;
        }
        return null;
    }
    
    // delete
    
    public E remove(int index) {
        if(isElementIndex(index)){
            return unlink(node(index));
        }
        return null;
    }

    public E poll() {
        E element = first.item;
        Node<E> next = first.next;
        first.item = null;
        first.next = null; 
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    public E pop() {
        final E element = last.item;
        final Node<E> prev = last.prev;
        last.item = null;
        last.prev = null; 
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }
    
    // util
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }
    
    Node<E> node(int index) {
        Node<E> elem = first;
        for (int i = 0; i < index; i++)
        elem = elem.next;
        return elem;
    }

    E unlink(Node<E> elem) {
        E element = elem.item;
        Node<E> next = elem.next;
        Node<E> prev = elem.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            elem.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            elem.next = null;
        }

        elem.item = null;
        size--;
        return element;
    }

    public int size() {
        return size;
    }

    // node (link)
    private static class Node<E> {

        Node<E> prev;
        E item;
        Node<E> next;

        Node(Node<E> prev, E elem, Node<E> next){
            this.prev = prev;
            this.item = elem;
            this.next = next;
        }
        
    }
}