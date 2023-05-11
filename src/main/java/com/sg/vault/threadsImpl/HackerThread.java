package com.sg.vault.threadsImpl;

import com.sg.vault.Vault;

public class HackerThread extends Thread{
    private Vault vault;

    public HackerThread(Vault vault){
        this.vault=vault;
        this.setName(this.getClass().getName());
        this.setPriority(Thread.MAX_PRIORITY);

    }

    @Override
    public void start(){
        System.out.println("Starting Thread "+this.getName());
        super.start();
    }
}
