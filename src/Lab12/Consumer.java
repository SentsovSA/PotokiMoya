package Lab12;

import java.util.concurrent.BlockingQueue;

class Consumer extends Thread {
    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            String word;
            int maxLength = 0;
            while (!(word = queue.take()).equals("EOF")) {
                if (word.length() > maxLength) {
                    maxLength = word.length();
                }
            }
            System.out.println("Максимальная длина слова: " + maxLength);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
