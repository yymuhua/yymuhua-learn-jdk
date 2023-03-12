package com.yymuhua.learn.spi;

import com.yymuhua.learn.spi.service.OneServiceFactoryByReflections;
import com.yymuhua.learn.spi.service.OneServiceFactoryByServiceLoader;

import java.util.ServiceLoader;

public class ServiceLoaderTest {
    public static void main(String[] args) {
        System.out.println(OneServiceFactoryByServiceLoader.getServiceMap());
        System.out.println(OneServiceFactoryByReflections.getServiceMap());
    }
}
