package com.example.demo.enums;

import lombok.Getter;

/**
 * Create BY zhang
 * Email zhangyingpeng123@163.com
 * Date 2019/7/17 0017
 *
 * 定义返回的枚举
 */
@Getter
public enum  ResultEnums {

    SUCCESS(1, "success"),
    FAIL(-1, "fail"),
    ;

    private Integer status;

    private String message;

    ResultEnums(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
