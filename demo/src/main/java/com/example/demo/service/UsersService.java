package com.example.demo.service;

import com.example.demo.bean.UserBean;
import com.example.demo.dao.UsersDAO;
import com.example.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Create BY zhang
 * Email zhangyingpeng123@163.com
 * Date 2019/7/19 0019
 */
@Service
@Slf4j
public class UsersService {

    @Resource
    private UsersDAO usersDAO;


    /**
     * 添加一个用户
     * @param bean
     */
    public void saveUserBean(UserBean bean) {
        Integer result = usersDAO.saveUsers(bean);
        if(1 == result) {
            log.info("【添加用户】添加成功");
        }else {
            log.error("【添加用户】添加失败");
        }
    }

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public UserBean findUserBeanByUserId(String userId) {
        UserBean userBean = usersDAO.findUserBeanByUserId(userId);
        if(null == userBean) {
            log.error("【查询用户】未查询到用户信息, userId = {}", userId);
            return null;
        }
        log.info("【查询用户】用户信息为 userBean = {}", userBean);
        return userBean;
    }

    /**
     * 修改用户手机号
     * @param bean
     */
    public void updatePhone(UserBean bean) {
        Integer result = usersDAO.updatePhoneByUserId(bean);
        if(1 == result) {
            log.info("【修改手机号】修改成功");
        }else {
            log.error("【修改手机号】修改失败");
        }
    }

    public void deleteUserBeanByUserId(String userId) {
        userId = "11111";
        Integer result = usersDAO.deleteUserBeanByUserId(userId);
        if(1 == result) {
            log.info("【删除用户】删除成功");
        }else {
            log.error("【删除用户】删除失败");
        }
    }
}
