package Lab12;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        Producer producer = new Producer(queue, "/Users/user/IdeaProjects/PotokiMoya/src/Lab12/input.txt");
        producer.start();

        Consumer consumer = new Consumer(queue);
        consumer.start();
    }
}
