package com.yymuhua.learn.concurrent.cases;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * 父子任务共用线程导致线程阻塞case
 * <a href="https://www.notion.so/JDK-67a54ba252be43caa2c0f3800e8025fc?pvs=4#a31ecbfc0dd443e2a30ff7fbefb54bb6">...</a>
 * 最终结果：task1,task2占有两个线程，subTask1和subTask2在阻塞队列中，导致线程池无法载接受新的请求！
 */
public class ThreadPoolBlocked {
    private static final ExecutorService POOL = new ThreadPoolExecutor(
            1,
            2,
            60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2),
            new ThreadPoolExecutor.AbortPolicy()
    );
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            final int finalI = i;
            try {
                POOL.submit(() -> {
                    try {
                        Future<Integer> task = POOL.submit(() -> {
                            return finalI;
                        });
                        doFutureGet(task);
                    } catch (Exception e) {
                        System.out.println("--> sub task" + finalI + "rejected! " + e.getMessage());
                    } finally {
                        System.out.println("task" + finalI + "finish!");
                    }
                });

            } catch (Exception e) {
                System.out.println("task" + finalI + "rejected! " + e.getMessage());
            }
        }
    }

    @SneakyThrows
    private static <T> T doFutureGet(Future<T> future) {
        return future.get();
    }
}
