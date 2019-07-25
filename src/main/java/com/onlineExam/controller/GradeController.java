package com.onlineExam.controller;

import com.onlineExam.common.QexzConst;
import com.onlineExam.dto.PageResult;
import com.onlineExam.model.Grade;
import com.onlineExam.model.Question;
import com.onlineExam.model.User;
import com.onlineExam.service.GradeService;
import com.onlineExam.service.QuestionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/grade")
public class GradeController {

    private static Log LOG = LogFactory.getLog(GradeController.class);

    @Autowired
    private GradeService gradeService;
    @Autowired
    private QuestionService questionService;

    //提交试卷
    @RequestMapping(value="/api/submitContest", method= RequestMethod.POST)
    @ResponseBody
    public PageResult submitContest(HttpServletRequest request, @RequestBody Grade grade) {
        PageResult pageResult = new PageResult();
        User currentUser = (User) request.getSession().getAttribute(QexzConst.CURRENT_ACCOUNT);
        List<String> answerStrs = Arrays.asList(grade.getAnswerJson().split(QexzConst.SPLIT_CHAR));
        int autoResult = 0;
        List<Question> questions = questionService.getQuestionsByContestId(grade.getContestId());

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if (question.getQuestionType() <= 1 && question.getAnswer()
                    .equals(answerStrs.get(i))) {
                autoResult += question.getScore();
            }
        }
        grade.setStudentId(currentUser.getId());
        grade.setResult(autoResult);
        grade.setAutoResult(autoResult);
        grade.setManulResult(0);
        int gradeId = gradeService.addGrade(grade);
        return new PageResult().setData(gradeId);
    }

    //完成批改试卷
    @RequestMapping(value="/api/finishGrade", method= RequestMethod.POST)
    @ResponseBody
    public PageResult finishGrade(@RequestBody Grade grade) {
        PageResult pageResult = new PageResult();
        grade.setResult(grade.getAutoResult()+grade.getManulResult());
        grade.setFinishTime(new Date());
        grade.setState(1);
        boolean result = gradeService.updateGrade(grade);
        return new PageResult().setData(result);
    }
}
