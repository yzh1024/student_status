package com.yzh1024.service;

import com.yzh1024.dao.ClazzDao;
import com.yzh1024.entity.Clazz;
import com.yzh1024.utils.BeanMapUtils;
import com.yzh1024.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yzh1024
 * @date 2020/9/10
 **/
@Service
public class ClazzService {
    @Autowired
    private ClazzDao clazzDao;
    public int create(Clazz pi){
        return clazzDao.create(pi);
    }

    public int delete(Integer id){
        return clazzDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Clazz clazz){
        return clazzDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(clazz)).addId(clazz.getId()).getMap());
    }

    public List<Clazz> query(Clazz clazz){
        return clazzDao.query(BeanMapUtils.beanToMap(clazz));
    }

    public Clazz detail(Integer id){
        return clazzDao.detail(MapParameter.getInstance().addId(id).getMap());
    }
    public int count(Clazz clazz){
        return clazzDao.count(BeanMapUtils.beanToMap(clazz));
    }

}
