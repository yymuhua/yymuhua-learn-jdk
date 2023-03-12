package com.yymuhua.learn.spi;

import com.yymuhua.common.spi.OneService;

public class OneServiceFromLog implements OneService {

    @Override
    public String identity() {
        return "from-log";
    }

    @Override
    public int doService() {
        return 0;
    }
}
