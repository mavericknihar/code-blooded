//Semaphores in Java languages(i have also updated the code in C language in the same repository..)
import java.util.concurrent.Semaphore;

class ReadersWriters {
    static Semaphore wsem, mutex;
    static int readcount;

    public static void main(String[] args) {
        int a = 1, b = 1;
        wsem = new Semaphore(1);
        mutex = new Semaphore(1);
        Thread r, w, r1, w1;

        r = new Thread(new Reader(a));
        a++;
        w1 = new Thread(new Writer(b));
        b++;
        r1 = new Thread(new Reader(a));
        w = new Thread(new Writer(b));

        r.start();
        w1.start();
        r1.start();
        w.start();

        try {
            r.join();
            w1.join();
            r1.join();
            w.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nMain Terminated.");
    }

    static class Reader implements Runnable {
        int c;

        public Reader(int c) {
            this.c = c;
        }

        public void run() {
            System.out.println("\nReader " + c + " is created");
            try {
                Thread.sleep(1000);
                mutex.acquire();
                readcount++;
                if (readcount == 1) {
                    wsem.acquire();
                }
                mutex.release();

                System.out.println("\n\nReader " + c + " is reading");
                Thread.sleep(1000);
                System.out.println("\nReader " + c + " is finished reading");

                mutex.acquire();
                readcount--;
                if (readcount == 0) {
                    wsem.release();
                }
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Writer implements Runnable {
        int c;

        public Writer(int c) {
            this.c = c;
        }

        public void run() {
            System.out.println("\nWriter " + c + " is created");
            try {
                Thread.sleep(1000);
                wsem.acquire();

                System.out.println("\nWriter " + c + " is writing");
                Thread.sleep(1000);
                System.out.println("\nWriter " + c + " finished writing");

                wsem.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
