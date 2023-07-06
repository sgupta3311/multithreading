package com.sg;

public class CheckForDataRace {
    public static void main(String[] args) {
        SharedClass sharedClass=new SharedClass();
        Thread t1= new Thread(()-> {
           for(int i=0; i<Integer.MAX_VALUE;i++){
               sharedClass.increment();
           }
        });
        Thread t2=new Thread(() ->{
           for (int i=Integer.MAX_VALUE;i>0; i--){
              sharedClass.decrement();
           }
        });

        Thread t3= new Thread(()-> {
            for(int i=0;i<Integer.MAX_VALUE; i++){
                sharedClass.raceCondition();
            }
        }) ;

        t1.start();
       // t2.start();
        t3.start();

    }
    public static class SharedClass{
        private volatile int x=0;
        private volatile int y=0;

        public void increment(){
            x++;
            y++;
        }
        public void decrement(){
            x--;
            y--;
        }
         public void raceCondition(){
            if(y>x){
                System.out.println("y>x - data race is detected "+ x + " -- " + y);
            }
         }

    }
}
