package Lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

class Producer extends Thread {
    private BlockingQueue<String> queue;
    private String filename;

    public Producer(BlockingQueue<String> queue, String filename) {
        this.queue = queue;
        this.filename = filename;
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("[\\s.,!?;:]+");
                for (String word : words) {
                    queue.put(word);
                }
            }
            queue.put("EOF");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
