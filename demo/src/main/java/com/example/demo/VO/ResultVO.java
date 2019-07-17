package com.example.demo.VO;

import lombok.Data;

/**
 * Create BY zhang
 * Email zhangyingpeng123@163.com
 * Date 2019/7/17 0017
 *
 * 接口返回体
 */
@Data
public class ResultVO<T> {

    private Integer status;

    private String message;

    private T date;
}
