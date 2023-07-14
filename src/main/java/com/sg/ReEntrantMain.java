package com.sg;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

 class ReEntrantThread implements Runnable {
    String name;
    ReentrantLock rentrantLock;

    public ReEntrantThread(ReentrantLock r1, String name){
        rentrantLock=r1;
        this.name=name;
    }
    public void run(){
        boolean done=false;
        while (!done){
            boolean ans= rentrantLock.tryLock();
            if(ans){
                try {
                    Date d=new Date();
                    SimpleDateFormat sf=new SimpleDateFormat("hh:mm:ss");
                    System.out.println("task name "+name+ " outer lock acquired at "+sf.format(d) + " Doing outer work");
                    Thread.sleep(1500);

                    // Getting Inner Lock
                    rentrantLock.lock();
                    try{
                        d=new Date();
                        sf=new SimpleDateFormat("hh:mm:ss");
                        System.out.println("task name - "+ name + " inner lock acquired at " + sf.format(d) + " Doing inner work");
                        System.out.println("Lock hold count - "+ rentrantLock.getHoldCount());
                        Thread.sleep(1500);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    finally {
                        System.out.println("task name - "+ name + " releasing Inner lock ");
                        rentrantLock.unlock();
                    }
                    System.out.println("Lock Hold Count - " + rentrantLock.getHoldCount());
                    System.out.println("task name - " + name + " work done");

                    done=true;
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    //Outer lock release
                    System.out.println("task name - "+ name + " releasing outer lock ");
                    rentrantLock.unlock();
                    System.out.println("Lock Hold Count - " + rentrantLock.getHoldCount());
                }
            }
            else{
                System.out.println("task name - " + name + " waiting for lock ");
            }
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
public class ReEntrantMain{
    static final int MAX_T = 2;
    public static void main(String[] args) {
        ReentrantLock rel = new ReentrantLock();
        ExecutorService pool= Executors.newFixedThreadPool(MAX_T);
        Runnable w1 = new ReEntrantThread(rel, "Job1");
        Runnable w2 = new ReEntrantThread(rel, "Job2");
        Runnable w3 = new ReEntrantThread(rel, "Job3");
        Runnable w4 = new ReEntrantThread(rel, "Job4");
        pool.execute(w1);
        pool.execute(w2);
        pool.execute(w3);
        pool.execute(w4);
        pool.shutdown();
    }
}

