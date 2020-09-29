package com.shadow.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "enjoy")
public class Lock3 {


    synchronized static void setA() throws Exception{
        log.info("---------A start");
        Thread.sleep(1000);
        setB();
        log.info("---------A end");
    }
    synchronized static void setB() throws Exception{
        log.info("---------B start");
        Thread.sleep(1000);
        log.info("---------B end");
    }

    public static void main(String[] args) throws Exception {

        setA();

        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                log.debug("t1------");

            } finally {
                //如果重入的次数和unlock的次数不同则不能完全释放锁
                lock.unlock();

            }
        }, "t1");
        t1.start();

        lock.lock();
        log.debug("main------");
        lock.unlock();
    }


    /**
     * 读写锁为什么不能升级？
     *
     * readWriteLock.r//  t1   t2  t3  t4
     *    td//t1 5
     *      readWriteLock.w  // 拿不到  t1等 t2 和t3 释放  t2 等t1 和t3
     *       td//t2
     *     readWriteLock.uw
     *     readWriteLock.rw
     *
     * 读写锁为什么可以降级?
     *
     *
     *
     */
}
