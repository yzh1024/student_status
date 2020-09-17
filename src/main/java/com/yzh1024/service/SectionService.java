package com.yzh1024.service;

import com.yzh1024.dao.SectionDao;
import com.yzh1024.entity.Section;
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
public class SectionService {
    @Autowired
    private SectionDao sectionDao;
    public int create(Section pi){
        return sectionDao.create(pi);
    }

    public int delete(Integer id){
        return sectionDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Section section){
        return sectionDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(section)).addId(section.getId()).getMap());
    }

    public List<Section> query(Section section){
        return sectionDao.query(BeanMapUtils.beanToMap(section));
    }

    public Section detail(Integer id){
        return sectionDao.detail(MapParameter.getInstance().addId(id).getMap());
    }
    public int count(Section section){
        return sectionDao.count(BeanMapUtils.beanToMap(section));
    }
    
}