package com.yzh1024.service;

import com.github.pagehelper.PageHelper;
import com.yzh1024.dao.UserDao;
import com.yzh1024.entity.User;
import com.yzh1024.utils.BeanMapUtils;
import com.yzh1024.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yzh1024
 * @date 2020/9/10
 **/
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public int create(User pi) {
        return userDao.create(pi);
    }

    public int delete(Integer id) {
        return userDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(User user) {
        return userDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(user)).addId(user.getId()).getMap());
    }

    public List<User> query(User user) {
        if (user != null && user.getPage() != null){
            PageHelper.startPage(user.getPage(), user.getLimit());
        }
        return userDao.query(BeanMapUtils.beanToMap(user));
    }

    public User detail(Integer id) {
        return userDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    /**
     * 登录验证
     *
     * @param userName
     * @param password
     * @return
     */
    public User login(String userName, String password) {
        Map<String, Object> map = MapParameter.getInstance()
                .add("userName", userName)
                .add("userPwd", password)
                .getMap();
        return userDao.detail(map);
    }

    public int count(User user) {
        return userDao.count(BeanMapUtils.beanToMap(user));
    }
}
