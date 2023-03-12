package com.yymuhua.learn.spi.service.impl;

import com.yymuhua.common.spi.OneService;

public class OneServiceImpl implements OneService {

    @Override
    public String identity() {
        return "first";
    }

    @Override
    public int doService() {
        return 1;
    }
}
