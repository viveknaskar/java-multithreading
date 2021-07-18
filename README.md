# java-multithreading

A thread is an operating system process which can run concurrently with other threads (or OS processes).

For implementing multithreading, there are two ways in which threads can be created:
1. By implementing the Runnable interface
2. By extending the Thread class

## Things to know
1. The start() method creates a new thread that executes the run() method.
   
2. The run() method just executes in the current thread, without starting a new thread.
   
3. volatile keyword is used to make classes thread safe. The keyword does not cache the value 
   of the variable but instead, it reads the variable from the main memory. It is used to modify 
   the value of a variable by different threads.
   
4. Synchronized blocks are marked with the synchronized keyword. All synchronized blocks are synchronized on the same 
   object can only have one thread executing inside them at a time. All other threads attempting to enter 
   the synchronized block are blocked until the thread inside the synchronized block exits the block.
   

