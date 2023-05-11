package com.sg.vault.threadsImpl;

import com.sg.vault.Vault;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExecutionMainThread {
    public static final int MAX_PASSWORD = 9999;
    public static void main(String[] args) {
        Random random=new Random();
        int randomNumber=random.nextInt(MAX_PASSWORD);
        System.out.println("Random number is "+randomNumber);
        Vault vault=new Vault(random.nextInt(randomNumber));
        List<Thread> threads=new ArrayList<>();
        threads.add(new AscendingThread(vault));
        threads.add(new DescendingThread(vault));
        threads.add(new PoliceThread());
        for (Thread thread : threads) {
            thread.start();
        }

    }
}
