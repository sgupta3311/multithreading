package com.sg.vault.threadsImpl;

import com.sg.vault.Vault;

public class AscendingThread extends HackerThread{
    private int MAX_PASSWORD;
    private Vault vault;
    public AscendingThread(Vault vault){
        super(vault);
    }
    @Override
    public void run(){

        for(int guess=0 ;guess < MAX_PASSWORD ;guess++){
            System.out.println("Ascending Thread.."+guess);
            if(vault.isCorrectPassword(guess)){
                System.out.println(this.getName() + "guessed the password "+guess);
                System.exit(0);
            }
        };
    }
}
