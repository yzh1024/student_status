package com.yzh1024.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yzh1024.entity.Score;

public interface ScoreDao {
    public int create(Score pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Score> query(Map<String, Object> paramMap);

    public Score detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);

    /**
     * 查询各科平均成绩
     * @param paramMap
     * @return
     */
    public List<HashMap> queryAvgBySection(Map<String, Object> paramMap);

    public List<HashMap> queryScoreByStudent(Map<String, Object> paramMap);
}