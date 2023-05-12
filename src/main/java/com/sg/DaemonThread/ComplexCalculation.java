package com.sg.DaemonThread;

import java.math.BigInteger;

public class ComplexCalculation {
    public static void main(String[] args) {
        ComplexCalculation complexCalculation=new ComplexCalculation();
        complexCalculation.calculateResult(BigInteger.valueOf(10l),BigInteger.valueOf(12l),BigInteger.valueOf(13l),BigInteger.valueOf(20l));
    }

    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2)  {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        PowerCalculatingThread thread1= new PowerCalculatingThread(base1,power1);
        PowerCalculatingThread thread2= new PowerCalculatingThread(base2,power2);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        result=thread1.getResult().add(thread2.getResult());
        System.out.println(result);
        return result;
    }
}
    class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }
        @Override
        public void run() {
            for(BigInteger i = BigInteger.ZERO;
                i.compareTo(power) !=0;
                i = i.add(BigInteger.ONE)) {
                result = base.multiply(result);
            }
        }

        public BigInteger getResult() { return result; }
    }
