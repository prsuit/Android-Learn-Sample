package com.prsuit.androidlearnsample.features.concurrent2.theory.aqs;



import com.prsuit.androidlearnsample.features.concurrent2.tools.SleepTools;

import java.util.concurrent.locks.Lock;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com 
 *
 *类说明：测试我们自己的独占锁的实现
 */
public class TestMyLock {

    public void test() {
        final Lock lock = new SelfLock();
        class Worker extends Thread {

			public void run() {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                try {
                    SleepTools.second(1);
                } finally {
                    lock.unlock();
                }
            }
        }
        // 启动4个子线程
        for (int i = 0; i < 4; i++) {
            Worker w = new Worker();
            //w.setDaemon(true);
            w.start();
        }
        // 主线程每隔1秒换行
        for (int i = 0; i < 10; i++) {
        	SleepTools.second(1);
            //System.out.println();
        }
    }

    public static void main(String[] args) {
        TestMyLock testMyLock = new TestMyLock();
        testMyLock.test();
    }
}
