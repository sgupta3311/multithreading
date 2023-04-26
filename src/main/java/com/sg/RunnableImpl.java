package com.sg;

public class RunnableImpl {

    public static void main(String[] args) {
        Runnable runnable= ()->{
            System.out.println("We are now in thread : "+Thread.currentThread().getName());
            System.out.println("Current Thread priority is "+Thread.currentThread().getPriority());
        };
        System.out.println("Before starting new thread "+Thread.currentThread().getName());
        Thread thread= new Thread(runnable);
        thread.setName("New worker thread.. ");
        thread.start();
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("After starting new thread "+Thread.currentThread().getName());
    }
}
