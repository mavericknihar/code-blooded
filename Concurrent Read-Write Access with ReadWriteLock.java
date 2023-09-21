/* A ReadWriteLock maintains a pair of associated locks, one for read-only operations and one for writing. The read lock may be held simultaneously by multiple reader threads,so long as there are no writers. The write lock is exclusive.

All ReadWriteLock implementations must guarantee that the memory synchronization effects of writeLock operations (as specified in the Lock interface) also hold with respect to the associated readLock. That is, a thread successfully acquiring the read lock will see all updates made upon previous release of the write lock.

A read-write lock allows for a greater level of concurrency in accessing shared data than that permitted by a mutual exclusion lock. It exploits the fact that while only a single thread at a time (a writer thread) can modify the shared data, in many cases any number of threads can concurrently read the data (hence reader threads). In theory, the increase in concurrency permitted by the use of a read-write lock will lead to performance improvements over the use of a mutual exclusion lock. In practice this increase in concurrency will only be fully realized on a multi-processor, and then only if the access patterns for the shared data are suitable. */
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExercise {
  private static final int NUM_READERS = 3;
  private static final int NUM_WRITERS = 2;

  public static void main(String[] args) {
    ReadWriteLock lock = new ReentrantReadWriteLock();
    SharedResource sharedResource = new SharedResource();

    // Create and start the reader threads
    for (int i = 0; i < NUM_READERS; i++) {
      Thread readerThread = new Thread(new Reader(lock, sharedResource));
      readerThread.start();
    }

    // Create and start the writer threads
    for (int i = 0; i < NUM_WRITERS; i++) {
      Thread writerThread = new Thread(new Writer(lock, sharedResource));
      writerThread.start();
    }
  }

  static class Reader implements Runnable {
    private ReadWriteLock lock;
    private SharedResource sharedResource;

    public Reader(ReadWriteLock lock, SharedResource sharedResource) {
      this.lock = lock;
      this.sharedResource = sharedResource;
    }

    public void run() {
      while (true) {
        lock.readLock().lock();

        // Read from the shared resource
        System.out.println("Reader " + Thread.currentThread().getName() + " reads: " + sharedResource.read());

        lock.readLock().unlock();

        // Delay between consecutive reads
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  static class Writer implements Runnable {
    private ReadWriteLock lock;
    private SharedResource sharedResource;
    private int counter = 0;

    public Writer(ReadWriteLock lock, SharedResource sharedResource) {
      this.lock = lock;
      this.sharedResource = sharedResource;
    }

    public void run() {
      while (true) {
        lock.writeLock().lock();

        // Write to the shared resource
        sharedResource.write("Writer " + Thread.currentThread().getName() + " writes: " + counter++);

        lock.writeLock().unlock();

        // Delay between consecutive writes
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  static class SharedResource {
    private String data;

    public String read() {
      return data;
    }

    public void write(String data) {
      this.data = data;
    }
  }
}
