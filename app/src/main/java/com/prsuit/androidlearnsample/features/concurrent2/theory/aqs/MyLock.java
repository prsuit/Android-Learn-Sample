package com.prsuit.androidlearnsample.features.concurrent2.theory.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description: 独占锁,不可重入
 * @Author: sh
 * @Date: 2021/11/14
 */
public class MyLock implements Lock {

    // 自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {

        /*判断处于占用状态*/
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /*获得锁*/
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /*释放锁*/
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /* 返回一个Condition，每个condition都包含了一个condition队列*/
        Condition newCondition(){return new ConditionObject();}
    }

    // 将操作代理到Sync上即可
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked(){
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads(){
        return sync.hasQueuedThreads();
    }
}
