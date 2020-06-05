package com.lagou.web.util;

public class TPData {
    private long tm;
    private long createTm;

    public TPData(long tm, long createTm) {
        this.tm = tm;
        this.createTm = createTm;
    }

    public long getTm() {
        return tm;
    }

    public long getCreateTm() {
        return createTm;
    }
}
