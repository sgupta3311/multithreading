package com.sg;

class ThreadClass extends Thread{


    @Override
    public void run() {
        System.out.println("Calling out new thread "+Thread.currentThread().getName());
    }


}

public class ThreadClassImpl{
    public static void main(String[] args) {
        ThreadClass thread=new ThreadClass();
        thread.setName("Thread Implementor Worker");
        thread.start();
    }
}
