package com.star.template.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class CharCache {

    private static final AtomicLong IDS = new AtomicLong();

    private static final ConcurrentMap<String, char[]> cache = new ConcurrentHashMap<String, char[]>();

    public static String put(char[] source) {
        String id = String.valueOf(IDS.incrementAndGet());
        cache.putIfAbsent(id, source);
        return id;
    }

    public static char[] getAndRemove(String id) {
        return cache.remove(id);
    }

}