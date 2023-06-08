package com.sg.datasharing;

public class ConcurrencyIssue2 {
    public static void main(String[] args) throws InterruptedException {
            InventoryCounter inventoryCounter=new InventoryCounter();
            IncrementingThread incrementingThread=new IncrementingThread(inventoryCounter);
            DecrementingThread decrementingThread=new DecrementingThread(inventoryCounter);
            incrementingThread.start();
            decrementingThread.start();
            incrementingThread.join();
            decrementingThread.join();
            System.out.println("We currently have " + inventoryCounter.getItems() + " items");

    }

    public static class IncrementingThread extends Thread{
        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter){
            this.inventoryCounter=inventoryCounter;
        }
        @Override
        public void run(){
            for (int i=0;i<1000;i++){
                inventoryCounter.increment();
               // System.out.println("Incrementing values --> "+inventoryCounter.getItems());
            }
        }
    }

    public static class DecrementingThread extends Thread{
        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter){
            this.inventoryCounter=inventoryCounter;
        }
        @Override
        public void run(){
            for(int i=0;i<1000;i++){
                inventoryCounter.decrement();
               // System.out.println("Decrementing values "+inventoryCounter.getItems());
            }
        }
    }
    private static class InventoryCounter{
        private int items=0;
        Object lock=new Object();
        public  void increment(){
            synchronized(this.lock) {
                items++;
            }
        }
        public  void decrement(){
            synchronized (this.lock) {
                items--;
            }
        }
        public int getItems(){
            return items;
        }
    }
}
