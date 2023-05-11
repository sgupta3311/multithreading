package com.sg.vault.threadsImpl;

import com.sg.vault.Vault;

public class DescendingThread extends HackerThread{
    private Vault vault;
    private int MAX_PASSWORD=9999;
    public DescendingThread(Vault vault){
        super(vault);
        this.vault=vault;
        this.setName("Descending Thread !!!!");
    }
    @Override
    public void run(){
        for(int guess=MAX_PASSWORD;guess>0;guess-- ){
            System.out.println("Descending Thread.." +guess);
            if(vault.isCorrectPassword(guess)) {
                System.out.println(this.getName() + "Correct password is "+guess);
                System.exit(0);
            }
        }
    }

}
