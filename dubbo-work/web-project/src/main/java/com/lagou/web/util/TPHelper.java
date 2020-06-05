package com.lagou.web.util;

import com.google.common.collect.Maps;
import javafx.application.Application;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.EmptyNode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TPHelper {
    private static Map<String, List<TPData>> data = new ConcurrentHashMap<>();
    private static final long M = 60 * 1000;

    /**
     * 调用添加记录
     */
    public static void add(String method, Long tm) {
        List<TPData> v = data.get(method);
        if (v == null) {
            data.putIfAbsent(method, new ArrayList<>());
            v = data.get(method);
        }
        v.add(new TPData(tm, System.currentTimeMillis()));
    }

    public static void calcAndShow() {
        delExpiredData();
        Map<String, List<TPData>> workingMap = new HashMap<>(data);
        sort(workingMap);
        // 计算 TP90
        System.out.println("--------- TP90 ------------");
        for (Map.Entry<String, List<TPData>> entry : data.entrySet()) {
            int size = entry.getValue().size();
            String method = entry.getKey();
            int lastIdx = (int) (size * 0.90);
            System.out.println("方法 " + method + ":" + entry.getValue().get(lastIdx).getTm());
        }
        System.out.println("--------- TP99 ------------");
        // 计算 TP99
        for (Map.Entry<String, List<TPData>> entry : data.entrySet()) {
            int size = entry.getValue().size();
            String method = entry.getKey();
            int lastIdx = (int) (size * 0.99);
            System.out.println("方法 " + method + ":" + entry.getValue().get(lastIdx).getTm());
        }
    }

    /**
     * 清除超过1分钟的记录
     */
    private static void delExpiredData() {
        long curr = System.currentTimeMillis();
        for (Map.Entry<String, List<TPData>> entry : data.entrySet()) {
            List<TPData> value = entry.getValue();
            Iterator<TPData> it = value.iterator();
            if (it.hasNext()) {
                TPData d = it.next();
                // 删除大于1分钟的数据
                if (curr - d.getCreateTm() >= M) {
                    it.remove();
                }
            }
        }
    }

    // 排序
    private synchronized static void sort(Map<String, List<TPData>> workingMap) {
        for (Map.Entry<String, List<TPData>> entry : workingMap.entrySet()) {
            List<TPData> value = entry.getValue();
            value.sort((o1, o2) -> (int) (o1.getTm() - o2.getTm()));
        }
    }


}
