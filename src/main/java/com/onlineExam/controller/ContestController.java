//package com.onlineExam.controller;
//
//import com.onlineExam.dto.PageResult;
//import com.onlineExam.model.Contest;
//import com.onlineExam.service.ContestService;
//import com.onlineExam.service.QuestionService;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(value = "/contest")
//public class ContestController {
//
//    private static Log LOG = LogFactory.getLog(ContestController.class);
//
//    @Autowired
//    private ContestService contestService;
//    @Autowired
//    private QuestionService questionService;
//
//    //添加考试
//    @RequestMapping(value="/api/addContest", method= RequestMethod.POST)
//    @ResponseBody
//    public PageResult addContest(@RequestBody Contest contest) {
//        PageResult pageResult = new PageResult();
//        int contestId = contestService.addContest(contest);
//        return new PageResult().setData(contestId);
//    }
//
//    //更新考试信息
//    @RequestMapping(value="/api/updateContest", method= RequestMethod.POST)
//    @ResponseBody
//    public PageResult updateContest(@RequestBody Contest contest) {
//        PageResult pageResult = new PageResult();
//        boolean result = contestService.updateContest(contest);
//        return new PageResult().setData(result);
//    }
//
//    //删除考试信息
//    @DeleteMapping("/api/deleteContest/{id}")
//    public PageResult deleteContest(@PathVariable int id) {
//        PageResult pageResult = new PageResult();
//        boolean result = contestService.deleteContest(id);
//        return new PageResult().setData(result);
//    }
//
//    //完成考试批改
//    @RequestMapping(value="/api/finishContest/{id}", method= RequestMethod.POST)
//    @ResponseBody
//    public PageResult finishContest(@PathVariable int id) {
//        PageResult pageResult = new PageResult();
//        Contest contest = contestService.getContestById(id);
//        contest.setState(3);
//        questionService.updateQuestionsStateByContestId(id, 1);
//        boolean result = contestService.updateContest(contest);
//        return new PageResult().setData(result);
//    }
//
//
//
//
//}
