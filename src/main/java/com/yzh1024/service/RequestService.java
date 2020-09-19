package com.yzh1024.service;

import com.github.pagehelper.PageHelper;
import com.yzh1024.dao.RequestDao;
import com.yzh1024.entity.Request;
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
public class RequestService {
    @Autowired
    private RequestDao requestDao;
    public int create(Request pi){
        return requestDao.create(pi);
    }

    public int delete(Integer id){
        return requestDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Request request){
        return requestDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(request)).addId(request.getId()).getMap());
    }

    public List<Request> query(Request request){
        if (request != null && request.getPage() != null){
            PageHelper.startPage(request.getPage(), request.getLimit());
        }
        return requestDao.query(BeanMapUtils.beanToMap(request));
    }

    public Request detail(Integer id){
        return requestDao.detail(MapParameter.getInstance().addId(id).getMap());
    }
    public int count(Request request){
        return requestDao.count(BeanMapUtils.beanToMap(request));
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
            flag = requestDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
        }
        return flag;
    }
    
}
