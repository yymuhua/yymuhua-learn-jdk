package com.yymuhua.learn.spi.service.impl;

import com.yymuhua.common.spi.OneService;

public class OneServiceImpl1 implements OneService {
    @Override
    public String identity() {
        return "second";
    }

    @Override
    public int doService() {
        return 2;
    }
}
