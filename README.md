# java-multithreading

A thread is an operating system process which can run concurrently with other threads (or OS processes).

For implementing multithreading, there are two ways in which threads can be created:
1. By implementing the Runnable interface
2. By extending the Thread class

### Things to know
1. The start() method creates a new thread that executes the run() method.
2. The run() method just executes in the current thread, without starting a new thread.
