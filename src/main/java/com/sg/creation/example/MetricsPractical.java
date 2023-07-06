package com.sg.creation.example;

import java.util.Random;

public class MetricsPractical {

    public static void main(String[] args) {
        Metrics metrics=new Metrics();
        MetricsPrinter metricsPrinter=new MetricsPrinter(metrics);
        BusinessLogic businessLogic=new BusinessLogic(metrics);
        metricsPrinter.start();
        businessLogic.start();
    }
    public static class MetricsPrinter extends Thread{
        private Metrics metrics;
        public MetricsPrinter(Metrics metrics){
            this.metrics=metrics;
        }
        @Override
        public void run(){
            while (true){
                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                double currentAverage=metrics.getAverage();
                System.out.println("Current average is "+ currentAverage);
            }
        }
    }

    public static class BusinessLogic extends Thread {
        private Metrics metrics;
        private Random random = new Random();

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                long start = System.currentTimeMillis();

                try {
                    int randomValue=random.nextInt(5);
                    //System.out.println("randomValue: "+randomValue);
                    Thread.sleep(randomValue);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                long end = System.currentTimeMillis();
                metrics.addSample(end-start);
            }

        }
    }
    public static class Metrics{
        private long count=0;
        private volatile  double average=0.0;

        public synchronized void addSample(long sample){
            double currentSum=average*count;
            count++;
            average=(currentSum+sample)/count;
        }
        public double getAverage(){
            return average;
        }
    }
}
