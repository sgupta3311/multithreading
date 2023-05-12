package com.sg.DaemonThread;

public class InterruptedExceptionDemo {
    public static void main(String[] args) {
        Thread thread=new Thread(new BlockingTask());
        thread.setName("Blocking Thread Demo");
        thread.start();
        thread.interrupt();
    }
}

class BlockingTask implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException interruptedException){
            System.out.println("Existing blocking thread interrupted.. ");
        }
    }
}
