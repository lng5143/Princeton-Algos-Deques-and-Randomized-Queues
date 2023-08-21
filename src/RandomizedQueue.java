import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int N = 0;

    //construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = array[i];
        }
        array = copy;
    }

    //is the randomized queue empty?
    public boolean isEmpty() {
        return (N == 0);
    }

    //return the number of items on the randomized queue
    public int size() {
        return N;
    }

    //add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        else {
            if (N == array.length)
                resize(array.length * 2);
            array[N++] = item;
        }
    }

    //remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        else {
            int itemIndex = StdRandom.uniformInt(0, N);
            Item item = array[itemIndex];
            array[itemIndex] = array[N - 1];
            array[N - 1] = null;
            N--;
            if (N == array.length / 4)
                resize(array.length / 2);
            return item;
        }
    }

    //return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        else {
            int itemIndex = StdRandom.uniformInt(0, N);
            Item item = array[itemIndex];
            return item;
        }
    }

    //return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int i = N;
        Integer[] randomNumber;

        public RandomizedQueueIterator() {
            randomNumber = new Integer[N];
            for (int j = 0; j < N; j++)
                randomNumber[j] = j;
            StdRandom.shuffle(randomNumber);
            /* //Knuth's shuffle
            for (int i = 1; i < N; i++) {
                int randomIndex = StdRandom.uniformInt(0, i);
                swap(randomNumber, i, randomIndex); */
        }

    @Override
    public boolean hasNext() {
        if (i > 0) return true;
        else return false;
    }

    @Override
    public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        else {
            return array[randomNumber[--i]];
        }
    }


    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}

    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        randomizedQueue.enqueue("a");
        randomizedQueue.enqueue("b");
        randomizedQueue.enqueue("c");
        randomizedQueue.enqueue("d");
        randomizedQueue.enqueue("e");
        randomizedQueue.enqueue("f");
        randomizedQueue.enqueue("g");
        randomizedQueue.enqueue("h");
        randomizedQueue.dequeue();
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.size());
        System.out.println(randomizedQueue.isEmpty());
        System.out.println(randomizedQueue.iterator().next());
        System.out.println(randomizedQueue.iterator().hasNext());
    }
}
