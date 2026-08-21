//Synchronization between multiple reader and writer threads to ensure data consistency and prevent conflicts in accessing a shared resource.
#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>

void *reader(void *);
void *writer(void *);

sem_t wsem, mutex;
int readcount = 0;

int main() {
    int a = 1, b = 1;
    sem_init(&wsem, 0, 1);
    sem_init(&mutex, 0, 1);
    pthread_t r, w, r1, w1;

    // Create reader and writer threads
    pthread_create(&r, NULL, reader, (void *)(intptr_t)a);
    a++;
    pthread_create(&w1, NULL, writer, (void *)(intptr_t)b);
    b++;
    pthread_create(&r1, NULL, reader, (void *)(intptr_t)a);
    pthread_create(&w, NULL, writer, (void *)(intptr_t)b);

    // Join threads to wait for their completion
    pthread_join(r, NULL);
    pthread_join(w1, NULL);
    pthread_join(r1, NULL);
    pthread_join(w, NULL);

    printf("\nMain Terminated.\n");
    return 0;
}

  void *reader(void *arg) {
      int c = (int)(intptr_t)arg;
      printf("\nReader %d is created", c);
      sleep(1);
      sem_wait(&mutex);
      readcount++;
        if (readcount == 1) 
        {
            sem_wait(&wsem);
        }
    sem_post(&mutex);

    // Reader reading
      printf("\n\nReader %d is reading\n", c);
      sleep(1);
      printf("\nReader %d is finished reading\n", c);

      sem_wait(&mutex);
      readcount--;
        if (readcount == 0) 
        {
          sem_post(&wsem);
        }
      sem_post(&mutex);

    return NULL;
}

  void *writer(void *arg) {
      int c = (int)(intptr_t)arg;
      printf("\nWriter %d is created\n", c);
      sleep(1);
      sem_wait(&wsem);
      printf("\nWriter %d is writing\n", c);
      sleep(1);
      printf("\nWriter %d finished writing\n", c);
      sem_post(&wsem);

    return NULL;
}
