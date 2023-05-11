package com.sg.vault.threadsImpl;

import javax.swing.*;

public class PoliceThread extends Thread{
@Override
    public void run(){
        for(int i=10;i>0 ;i--){
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Time is running,fast: "+i);
        }
    System.out.println("Siren blown, game over..");
        System.exit(0);
}
}
