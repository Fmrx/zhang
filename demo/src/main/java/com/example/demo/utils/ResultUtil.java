package com.example.demo.utils;

import com.example.demo.VO.ResultVO;
import com.example.demo.enums.ResultEnums;

/**
 * Create BY zhang
 * Email zhangyingpeng123@163.com
 * Date 2019/7/17 0017
 *
 * 定义接口返回的工具类
 */
public class ResultUtil {

    /**
     * 无参的成功返回
     * @return
     */
    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setStatus(ResultEnums.SUCCESS.getStatus());
        resultVO.setMessage(ResultEnums.SUCCESS.getMessage());
        return resultVO;
    }

    /**
     * 有参的成功返回
     * @param object
     * @return
     */
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setStatus(ResultEnums.SUCCESS.getStatus());
        resultVO.setMessage(ResultEnums.SUCCESS.getMessage());
        resultVO.setDate(object);
        return resultVO;
    }
}
