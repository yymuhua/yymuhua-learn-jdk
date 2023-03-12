package com.yymuhua.learn.spi.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.yymuhua.common.spi.OneService;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class OneServiceFactoryByReflections {
    private static final String ROOT_PACKAGES = "com.yymuhua";
    private static final Map<String, OneService> SERVICE_MAP;
    static {
        SERVICE_MAP = Maps.newHashMap();
        Reflections reflections = getReflections();
        for (Class<? extends OneService> serviceClass : reflections.getSubTypesOf(OneService.class)) {
            try {
                OneService oneService = serviceClass.newInstance();
                SERVICE_MAP.put(oneService.identity(), oneService);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private static Reflections getReflections() {
        List<URL> packagePaths = Lists.newArrayList();
        packagePaths.addAll(ClasspathHelper.forPackage(ROOT_PACKAGES));
        return new Reflections(new ConfigurationBuilder().setUrls(packagePaths).setScanners(new SubTypesScanner()));
    }

    public static OneService getService(String identity) {
        return SERVICE_MAP.get(identity);
    }

    public static Map<String, OneService> getServiceMap() {
        return SERVICE_MAP;
    }
}
