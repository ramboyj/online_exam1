package com.onlineExam.controller;

import com.onlineExam.dto.PageResult;
import com.onlineExam.model.Question;
import com.onlineExam.service.QuestionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    private static Log LOG = LogFactory.getLog(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    //添加题目
    @RequestMapping(value="/api/addQuestion", method= RequestMethod.POST)
    @ResponseBody
    public PageResult addQuestion(@RequestBody Question question) {
        PageResult pageResult = new PageResult();
        int questionId = questionService.addQuestion(question);
        return new PageResult().setData(questionId);
    }

    //更新题目信息
    @RequestMapping(value="/api/updateQuestion", method= RequestMethod.POST)
    @ResponseBody
    public PageResult updateQuestion(@RequestBody Question question) {
        PageResult pageResult = new PageResult();
        boolean result = questionService.updateQuestion(question);
        return new PageResult().setData(result);
    }

    //删除题目信息
    @DeleteMapping("/api/deleteQuestion/{id}")
    public PageResult deleteContest(@PathVariable int id) {
        PageResult pageResult = new PageResult();
        boolean result = questionService.deleteQuestion(id);
        return new PageResult().setData(result);
    }
}
