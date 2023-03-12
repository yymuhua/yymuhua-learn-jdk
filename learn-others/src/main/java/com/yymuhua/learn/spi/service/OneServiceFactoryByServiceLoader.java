package com.yymuhua.learn.spi.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yymuhua.common.spi.OneService;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

public class OneServiceFactoryByServiceLoader {
    private static final Map<String, OneService> SERVICE_MAP = Maps.newHashMap();
    static {
        ServiceLoader<OneService> load = ServiceLoader.load(OneService.class);
        load.forEach(service -> SERVICE_MAP.put(service.identity(), service));
    }

    public static OneService getService(String identity) {
        return SERVICE_MAP.get(identity);
    }

    public static Map<String, OneService> getServiceMap() {
        return SERVICE_MAP;
    }
}
