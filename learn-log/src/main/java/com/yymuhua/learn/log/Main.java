package com.yymuhua.learn.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {
        Logger asyncLogger = LoggerFactory.getLogger("async");
        Logger syncLogger = LoggerFactory.getLogger("sync");
        syncLogger.info("同步打印的日志");
        asyncLogger.info("异步打印的日志");
    }
}
