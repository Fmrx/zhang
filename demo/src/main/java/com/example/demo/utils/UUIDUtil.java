package com.example.demo.utils;

import java.util.UUID;

/**
 * Create BY zhang
 * Email zhangyingpeng123@163.com
 * Date 2019/7/19 0019
 */
public class UUIDUtil {

    public static String getUUID(){
        String UUID = java.util.UUID.randomUUID().toString().replaceAll("-", "");
        return UUID;
    }
}
