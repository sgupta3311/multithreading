package com.sg.DaemonThread;

public class DaemonThread extends Thread{
     DaemonThread(String name){
        super(name);
    }

    DaemonThread(){
    }

    public void run(){
         if(Thread.currentThread().isDaemon()){
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
             System.out.println(this.getName()+" is Daemon Thread");
         }
         else {
             System.out.println(this.getName()+ " is User Thread");
         }
    }

    public static void main(String[] args) {
        DaemonThread firstThread= new DaemonThread("first Thread");
        DaemonThread secondThread= new DaemonThread("second Thread");
        DaemonThread thirdThread= new DaemonThread("third Thread");
        DaemonThread forthThread= new DaemonThread();
        firstThread.setDaemon(true);
        firstThread.start();
        secondThread.start();
        thirdThread.setDaemon(true);
        thirdThread.start();
        forthThread.setName("forth thread");
        forthThread.start();

    }
}
