package com.example.demo.controllers;

import com.example.demo.VO.ResultVO;
import com.example.demo.bean.UserBean;
import com.example.demo.service.UsersService;
import com.example.demo.utils.ResultUtil;
import com.example.demo.utils.UUIDUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Create BY zhang
 * Email zhangyingpeng123@163.com
 * Date 2019/7/19 0019
 */
@RestController
@RequestMapping("/user")
public class UsersController {

    @Resource
    private UsersService usersService;

    @RequestMapping(value = "/saveUserBean", method = RequestMethod.GET)
    public ResultVO saveUserBean(){
        UserBean userBean = new UserBean();
        userBean.setUserId(UUIDUtil.getUUID());
        userBean.setPhone("1111111111");
        userBean.setName("mrz");
        usersService.saveUserBean(userBean);

        return ResultUtil.success();
    }

    @RequestMapping(value = "/userDetails", method = RequestMethod.GET)
    public ResultVO userDetails(){
        String userId = "11111";
        UserBean userBean =usersService.findUserBeanByUserId(userId);
        return ResultUtil.success(userBean);

    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ResultVO update() {
        UserBean userBean = new UserBean();
        userBean.setPhone("2222222");
        userBean.setUserId("11111");
        usersService.updatePhone(userBean);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResultVO delete() {
        String userId = "1111";
        usersService.deleteUserBeanByUserId(userId);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResultVO login(HttpSession session) {
        session.setAttribute("userId", "123");
        return ResultUtil.success();
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResultVO get(HttpSession session) {
        String userId= session.getAttribute("userId").toString();
        return ResultUtil.success(userId);
    }



}
