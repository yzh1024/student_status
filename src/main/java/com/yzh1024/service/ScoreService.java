package com.yzh1024.service;

import com.yzh1024.dao.ScoreDao;
import com.yzh1024.entity.Score;
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
public class ScoreService {
    @Autowired
    private ScoreDao scoreDao;
    public int create(Score pi){
        return scoreDao.create(pi);
    }

    public int delete(Integer id){
        return scoreDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Score score){
        return scoreDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(score)).addId(score.getId()).getMap());
    }

    public List<Score> query(Score score){
        return scoreDao.query(BeanMapUtils.beanToMap(score));
    }

    public Score detail(Integer id){
        return scoreDao.detail(MapParameter.getInstance().addId(id).getMap());
    }
    public int count(Score score){
        return scoreDao.count(BeanMapUtils.beanToMap(score));
    }
    
}
