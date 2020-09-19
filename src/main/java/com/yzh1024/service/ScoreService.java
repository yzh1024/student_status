package com.yzh1024.service;

import com.github.pagehelper.PageHelper;
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
        if (score != null && score.getPage() != null){
            PageHelper.startPage(score.getPage(), score.getLimit());
        }
        return scoreDao.query(BeanMapUtils.beanToMap(score));
    }

    public Score detail(Integer id){
        return scoreDao.detail(MapParameter.getInstance().addId(id).getMap());
    }
    public int count(Score score){
        return scoreDao.count(BeanMapUtils.beanToMap(score));
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
            flag = scoreDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
        }
        return flag;
    }
    
}
