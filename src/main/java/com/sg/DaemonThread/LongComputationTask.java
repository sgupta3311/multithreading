package com.sg.DaemonThread;

import java.math.BigInteger;

public class LongComputationTask {
    public static void main(String[] args) {
        LongComputationTaskImpl longComputationTask = new LongComputationTaskImpl(new BigInteger("30000000000000"), new BigInteger("400015465465465"));
        Thread thread = new Thread(longComputationTask);
        thread.start();
        thread.interrupt();
    }
}


class LongComputationTaskImpl implements Runnable {
    private final BigInteger base;
    private final BigInteger power;

    public LongComputationTaskImpl(BigInteger base, BigInteger power) {
        this.base = base;
        this.power = power;
    }

    BigInteger getPower() {
        BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely Interrupted Condition ");
                    return BigInteger.ZERO;
                }
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
