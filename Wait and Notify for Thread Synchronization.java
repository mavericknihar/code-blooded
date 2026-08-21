// ProducerConsumer.java
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
  private static final int BUFFER_SIZE = 5;
  private static final Queue < Integer > buffer = new LinkedList < > ();

  public static void main(String[] args) {
    Thread producerThread = new Thread(new Producer());
    Thread consumerThread = new Thread(new Consumer());

    producerThread.start();
    consumerThread.start();
  }

  static class Producer implements Runnable {
    public void run() {
      int value = 0;
      while (true) {
        synchronized(buffer) {
          // Wait if the buffer is full
          while (buffer.size() == BUFFER_SIZE) {
            try {
              buffer.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }

          System.out.println("Producer produced: " + value);
          buffer.add(value++);

          // Notify the consumer that an item is produced
          buffer.notify();

          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  static class Consumer implements Runnable {
    public void run() {
      while (true) {
        synchronized(buffer) {
          // Wait if the buffer is empty
          while (buffer.isEmpty()) {
            try {
              buffer.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }

          int value = buffer.poll();
          System.out.println("Consumer consumed: " + value);

          // Notify the producer that an item is consumed
          buffer.notify();

          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}
