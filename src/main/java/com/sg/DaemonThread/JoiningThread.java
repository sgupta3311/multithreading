package com.sg.DaemonThread;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class JoiningThread {
    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(200L, 3435L, 2324L, 4656L, 23L, 5556L);

        List<Thread> threads=new ArrayList<>();
        for( Long inputNumber : inputNumbers) {
            threads.add(new FactoralNumber(inputNumber));
        }
        for(Thread thread : threads){
           // thread.setDaemon(true);
            thread.start();
            thread.join(2000);

        }
        for(int i=0;i<inputNumbers.size();i++){
            FactoralNumber facrorialThread= (FactoralNumber) threads.get(i);
            if(facrorialThread.isFinished()){
                System.out.println("Running status: "+facrorialThread.isFinished()+" , for input number "+ inputNumbers.get(i) + ",Result: "+ facrorialThread.getResult() );
            }
        }
    }
}
    class FactoralNumber extends Thread{
        private long inputNumber;
        private BigInteger result=BigInteger.ZERO;
        private boolean isFinished=false;

        public FactoralNumber(long inputNumber){
            this.inputNumber=inputNumber;
        }
        @Override
        public void run(){
            this.result=factorial(inputNumber);
            System.out.println("Computed value is "+result);
            this.isFinished=true;
        }

        public BigInteger factorial(Long n){
            BigInteger tempResult=BigInteger.ONE;
            for(int i=0;i <=n ; i++){
                tempResult=  tempResult.multiply(BigInteger.valueOf(n));
            }
            return tempResult;
        }

        public Boolean isFinished(){
            return isFinished;
        }

        public BigInteger getResult(){
            return result;
        }

    }

