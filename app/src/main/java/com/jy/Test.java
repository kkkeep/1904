package com.jy;

/*
 * created by Cherry on 2019-11-25
 **/
public class Test {

    public static void main(String args[]) throws InterruptedException {



      Thread thread =   new Thread(){
            @Override
            public void run() {



                for (int i = 0; i < 10; i++) {
                    System.out.println("thread " + Thread.currentThread().getName() + " print " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        };

        thread.setDaemon(true);

        thread.start();

        System.out.println("thread " + Thread.currentThread().getName() + " 准备睡觉");


        Thread.sleep(10000);

        System.out.println("thread " + Thread.currentThread().getName() + " 即将结束");

    }


}
