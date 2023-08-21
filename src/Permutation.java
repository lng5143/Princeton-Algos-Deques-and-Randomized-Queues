import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int o = k;
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        for (int i = 0; i < k; i++) {
            String input = StdIn.readString();
            randomizedQueue.enqueue(input);
        }

        while (!StdIn.isEmpty()) {
            int j = StdRandom.uniformInt(0, o + 1);
            String input = StdIn.readString();
            if (j < k) {
                randomizedQueue.dequeue();
                randomizedQueue.enqueue(input);
            }
            o++;
        }
        for (int i = 0; i < k; i++)
            System.out.println(randomizedQueue.dequeue());
    }
}


/*public class Permutation {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int j;
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        for (int i = 1; i <= a; i++) {
            String input = StdIn.readString();
            queue.enqueue(input);
        }
        for (j = a + 1; !StdIn.isEmpty(); j++) {
            String input = StdIn.readString();
            int c = StdRandom.uniformInt(1, j + 1);
            if (c <= a) {
                queue.dequeue();
                queue.enqueue(input);
            }
        }
        for (String g : queue) System.out.println(g);
    }
}*/
