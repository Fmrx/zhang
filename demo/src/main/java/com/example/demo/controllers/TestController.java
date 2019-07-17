package com.example.demo.controllers;

import com.example.demo.VO.ResultVO;
import com.example.demo.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {

    @GetMapping(value = "/test")
    public ResultVO test(){
        log.info("【测试】返回成功");
        return ResultUtil.success();
    }
}
