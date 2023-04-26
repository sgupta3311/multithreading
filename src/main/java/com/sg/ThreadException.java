package com.sg;

public class ThreadException {

    Runnable runnable=()->{
        System.out.println("We are now in thread "+Thread.currentThread().getName());
    };
    Thread thread=new Thread(runnable);


}
