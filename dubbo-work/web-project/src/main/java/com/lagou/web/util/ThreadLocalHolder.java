package com.lagou.web.util;

public class ThreadLocalHolder {
    private static ThreadLocal<String> localData = new ThreadLocal<String>() {
        protected String initialValue() {
            return "";
        }
    };

    public static void setIp(String ip) {
        localData.set(ip);
    }

    public static String getIp() {
        return localData.get();
    }
}
