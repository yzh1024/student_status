package com.yzh1024.controller;

import com.yzh1024.entity.Score;
import com.yzh1024.entity.Score;
import com.yzh1024.entity.Student;
import com.yzh1024.entity.Subject;
import com.yzh1024.service.ScoreService;
import com.yzh1024.service.ScoreService;
import com.yzh1024.service.SubjectService;
import com.yzh1024.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yzh1024
 * @date 2020/9/10
 **/

@Controller
@RequestMapping("/score")
public class ScoreController {
    private final String LIST = "score/list";
    private final String ADD = "score/add";
    private final String UPDATE = "score/update";

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private SubjectService subjectService;

    /**
     * 跳转到score/list.jsp
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return "score/list";
    }

    /**
     * 新增，先查出专业，然后跳到score/add.jsp页面
     * @return
     */
    @GetMapping("/add")
    private String add(ModelMap modelMap){
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("subjects",subjects);
        return ADD;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    private Map<String, Object> delete(String ids) {
        int result = scoreService.delete(ids);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    /**
     * 修改信息
     * 先根据id查询信息，然后跳转到score/update.jsp页面
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    private String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        Score score = scoreService.detail(id);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("score",score);
        modelMap.addAttribute("subjects",subjects);
        return UPDATE;
    }


    @PostMapping("/create")
    @ResponseBody
    private Map<String, Object> create(String sectionIds, String courseIds, HttpSession session) {
        Student student = (Student) session.getAttribute("user");
        int result = scoreService.create(sectionIds,courseIds,student.getId());
        //新增失败
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().put("date",new Date()).getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    private Map<String, Object> delete(@PathVariable("id") Integer id) {
        int result = scoreService.delete(id);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }



    @PostMapping("/update")
    @ResponseBody
    private Map<String, Object> update(Score score) {
        int result = scoreService.update(score);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    private Map<String, Object> query(Score score) {
        List<Score> scorees = scoreService.query(score);
        List<Subject> subjects = subjectService.query(null);
        Integer count = scoreService.count(score);
        return MapControl.getInstance().success().put("data",scorees).put("count",count).getMap();
    }

}














