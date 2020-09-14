package com.shadow.test;

import lombok.extern.slf4j.Slf4j;



/**
 * 1、什么是对象头？
 *  什么是对象？内存级别而言来研究什么是对象
 */

@Slf4j(topic = "enjoy")
public class Test1 {
    static  Object key = new Object();
    static  Object key1 = new Object();
    static  Object key2 = new Object();
    static boolean isPrettyGril = false;


    /**
     * 多线程情况下 假设某个线程拿到锁了，但是他需要满足某个条件
     * 才能执行 如果用sleep 会导致在没有满足条件的情况下；我一直阻塞
     * 一直持有锁，别的线程也拿不到锁
     * 得有办公室的key
     * boss 需要叫jack 去加班编程；还有其他十个人是自愿加班
     * jack不愿加班---你给我女人
     * @param args
     * @throws InterruptedException
     */

    public  static   void main(String[] args) throws InterruptedException {
        //jack
        new Thread(() -> {
            synchronized (key) {
                System.out.println("有没有女人[{}]"+isPrettyGril);
                if (!isPrettyGril) {
                    System.out.println("没有女人！等女人，等5秒;别人也不能干活");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("等了5秒 有没有女人？[{}]"+ isPrettyGril);
                if (isPrettyGril) {
                    System.out.println("------男女搭配干活不累；啪啪啪写完了代码");
                }else{
                    System.out.println("------下班回家----");
                }
            }
        }, "jack").start();




        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                    synchronized (key1) {
                        System.out.println("我们10个屌丝工作了");
                    }
                }, "其它人").start();
        }


        Thread.sleep(1000);
        new Thread(() -> {
            synchronized (key2) {
                isPrettyGril = true;
                System.out.println("桥本有菜来了");
            }
        }, "boss").start();

    }


   public void aa(){
       this.isPrettyGril = true;
   }

    public   void cc(){
        Class<Test1> test1Class = Test1.class;
        synchronized (this){

        }
    }


}
