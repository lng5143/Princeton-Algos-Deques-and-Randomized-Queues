import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int numberOfItems = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    //construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    //is the deque empty
    public boolean isEmpty(){
        return (numberOfItems == 0);
    }

    //return the number of items on the deque
    public int size() {
        return numberOfItems;
    }

    //add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.prev = null;
            first.next = oldFirst;
            if (isEmpty()) last = first;
            else {
                oldFirst.prev = first;
            }
            numberOfItems++;
        }
    }

    //add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = oldLast;
            if (isEmpty()) first = last;
            else {
                oldLast.next = last;
            }
            numberOfItems++;
        }
    }

    //remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        else {
            Item item = first.item;
            first = first.next;
            numberOfItems--;
            if (isEmpty()) last = null;
            else first.prev = null;
            return item;
        }
    }

    //remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        else {
            Item item = last.item;
            last = last.prev;
            numberOfItems--;
            if (isEmpty()) first = null;
            else last.next = null;
            return item;
        }
    }

    //return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    //unit testing
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        //deque.addFirst("b");
        //deque.addFirst("a");
        deque.addLast("c");
        //deque.addLast("d");
        //deque.removeFirst();
        deque.removeLast();
        System.out.println(deque.isEmpty());
        System.out.println(deque.size());
        System.out.println(deque.iterator().hasNext());
        System.out.println(deque.iterator().next());
    }
}