package com.yzh1024.dao;

import com.yzh1024.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author yzh1024
 * @date 2020/9/10
 **/
public interface UserDao {
    /**
     * 增加用户
     * @param user
     * @return
     */
    int create(User user);

    /**
     * 删除用户
     * @param map
     * @return
     */
    int delete(Map<String,Object> map);

    /**
     * 更新用户
     * @param map
     * @return
     */
    int update(Map<String,Object> map);

    /**
     * 查询
     * @param map
     * @return
     */
    List<User> query(Map<String,Object> map);

    /**
     * 分页
     * @param map
     * @return
     */
    int count(Map<String,Object> map);

    /**
     * 详细查询
     * @param map
     * @return
     */
    User detail(Map<String,Object> map);
}
