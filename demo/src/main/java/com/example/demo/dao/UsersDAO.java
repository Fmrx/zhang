package com.example.demo.dao;

import com.example.demo.bean.UserBean;
import org.apache.ibatis.annotations.*;

/**
 * Create BY zhang
 * Email zhangyingpeng123@163.com
 * Date 2019/7/19 0019
 */
@Mapper
public interface UsersDAO {

    /**
     * 增加一个用户
     * @param bean
     * @return
     */
    @Insert("insert into users(user_id, name, phone) values(#{bean.userId}, #{bean.name}, #{bean.phone})")
    Integer saveUsers(@Param("bean") UserBean bean);

    /**
     * 查询一个用户
     * @param userId
     * @return
     */
    @Select("select user_id as userId, name as name, phone as phone from users where user_id = #{userId}")
    UserBean findUserBeanByUserId(@Param("userId") String userId);

    /**
     * 修改用户手机号
     * @param bean
     * @return
     */
    @Update("update users set phone = #{bean.phone} where user_id = #{bean.userId}")
    Integer updatePhoneByUserId(@Param("bean") UserBean bean);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Delete("delete from users where user_id = #{userId}")
    Integer deleteUserBeanByUserId(@Param("userId")String userId);
}
