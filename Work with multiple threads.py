import threading
class thread(threading.Thread):
    def __init__(self,thread,Thread_ID):
        threading.Thread.__init__(self)
        self.thread_name = thread.name
        self.thread_ID = thread_ID
        def run(self):
             print(str(self.thread_name)+ ""+ str(self.thread_ID))
             thread1 = thread("MGM", 1000)
             thread2 = thread("Thread", 2000)
             thread1.start()
             thread1.start()
             print("exit")
