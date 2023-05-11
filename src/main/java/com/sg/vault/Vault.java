package com.sg.vault;

public class Vault {
    private int password;

    public Vault(Integer password){
        this.password=password;
    }
    public boolean isCorrectPassword(int guess){
        return this.password==guess;
    }
}


