package com.yymuhua.learn.concurrent.delay;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DelayScheduleTest {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(
            5, 10, 60, TimeUnit.SECONDS, new SynchronousQueue<>());
    public static void main(String[] args) throws InterruptedException {
        DelayScheduler scheduler = new DelayScheduler(THREAD_POOL);
        System.out.println("begin: " + currentTime());
        scheduler.submit(new DelayScheduler.DelayTask(1000) {
            @Override
            public void doDelay() {
                System.out.println("task1: " + currentTime());
            }
        });
        scheduler.submit(new DelayScheduler.DelayTask(500) {
            @Override
            public void doDelay() {
                System.out.println("task2: " + currentTime());
            }
        });
        scheduler.submit(new DelayScheduler.DelayTask(1500) {
            @Override
            public void doDelay() {
                System.out.println("task3: " + currentTime());
            }
        });
        Thread.sleep(5000);
        scheduler.close();
    }

    private static String currentTime() {
        return DATE_FORMAT.format(System.currentTimeMillis());
    }
}
