package com.yzh1024.service;

import com.github.pagehelper.PageHelper;
import com.yzh1024.dao.UserDao;
import com.yzh1024.entity.User;
import com.yzh1024.utils.BeanMapUtils;
import com.yzh1024.utils.MD5Utils;
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


    /**
     * 创建用户，密码加密处理
     * @param pi
     * @return
     */
    public int create(User pi) {
        pi.setUserPwd(MD5Utils.getMD5(pi.getUserPwd()));
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
    public int count(User user) {
        return userDao.count(BeanMapUtils.beanToMap(user));
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
    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public int delete(String ids) {
        //按照“,”将传过来的字符串分割
        String[] arr = ids.split(",");
        int flag = 0;
        for (String s : arr) {
            flag = userDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
        }
        return flag;
    }

}
