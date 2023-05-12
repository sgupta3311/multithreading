package com.sg.DaemonThread;

import java.math.BigInteger;

public class SetDaemonTrue {
    public static void main(String[] args) {
        LongComputationTaskImpl longComputationTask = new LongComputationTaskImpl(new BigInteger("300"), new BigInteger("400"));
        Thread thread = new Thread(longComputationTask);
        thread.setDaemon(true);
        thread.start();
        thread.interrupt();
    }
}


class LongComputationTaskImpl2 implements Runnable {
    private final BigInteger base;
    private final BigInteger power;

    public LongComputationTaskImpl2(BigInteger base, BigInteger power) {
        this.base = base;
        this.power = power;
    }

    BigInteger getPower() {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(base);
        }
        System.out.println(base + "^" + power + "=" + result);
        return result;
    }

    @Override
    public void run() {
        System.out.println(base + "^" + power + "=" + getPower());
    }
}
