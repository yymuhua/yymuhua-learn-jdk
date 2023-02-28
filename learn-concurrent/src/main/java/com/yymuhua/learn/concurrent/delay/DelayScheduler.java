package com.yymuhua.learn.concurrent.delay;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DelayScheduler {
    private volatile boolean runnable = true;
    private final ExecutorService executeThreadPool;
    private final DelayQueue<DelayTask> taskQueue = new DelayQueue<>();
    public DelayScheduler(ExecutorService threadPool) {
        this.executeThreadPool = threadPool;
        new Thread(this::dispatch).start();
    }
    private void dispatch() {
        while (runnable) {
            try {
                DelayTask task = taskQueue.take();
                executeThreadPool.submit(task::doDelay);
            } catch (Exception e) {
                log.error("DelayScheduler#dispatch error!", e);
            }
        }
    }

    public void submit(DelayTask task) {
        taskQueue.add(task);
    }

    public void close() {
        runnable = false;
    }

    static abstract class DelayTask implements Delayed {
        private final long expireTimestamp;

        public DelayTask(long delayInMills) {
            this.expireTimestamp = System.currentTimeMillis() + delayInMills;
        }

        public abstract void doDelay();

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expireTimestamp - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}
