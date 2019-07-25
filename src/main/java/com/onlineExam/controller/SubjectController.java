package com.onlineExam.controller;

import com.onlineExam.common.QexzConst;
import com.onlineExam.dto.PageResult;
import com.onlineExam.model.Subject;
import com.onlineExam.service.SubjectService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    private static Log LOG = LogFactory.getLog(SubjectController.class);

    @Autowired
    private SubjectService subjectService;

    //添加课程
    @RequestMapping(value="/api/addSubject", method= RequestMethod.POST)
    @ResponseBody
    public PageResult addSubject(@RequestBody Subject subject) {
        PageResult pageResult = new PageResult();
        subject.setImgUrl(QexzConst.DEFAULT_SUBJECT_IMG_URL);
        subject.setQuestionNum(0);
        int subjectId = subjectService.addSubject(subject);
        return new PageResult().setData(subjectId);
    }

    //更新课程
    @RequestMapping(value="/api/updateSubject", method= RequestMethod.POST)
    @ResponseBody
    public PageResult updateSubject(@RequestBody Subject subject) {
        PageResult pageResult = new PageResult();
        boolean result = subjectService.updateSubject(subject);
        return new PageResult().setData(result);
    }

    //删除课程
    @DeleteMapping("/api/deleteSubject/{id}")
    public PageResult deleteSubject(@PathVariable int id) {
        PageResult pageResult = new PageResult();
        boolean result = subjectService.deleteSubjectById(id);
        return new PageResult().setData(result);
    }

    /**
     * 分页获取所有课程列表
     */
    @RequestMapping(value = "/api/getSubjects", method = RequestMethod.POST)
    @ResponseBody
    public PageResult getSubjects(HttpServletRequest request, HttpServletResponse response) {
        PageResult pageResult = new PageResult();
//        try {
//            String username = request.getParameter("username");
//            String password = request.getParameter("password");
//            User current_account = accountService.getAccountByUsername(username);
//            if(current_account != null) {
//                String pwd = MD5.md5(QexzConst.MD5_SALT+password);
//                if(pwd.equals(current_account.getPassword())) {
//                    request.getSession().setAttribute(QexzConst.CURRENT_ACCOUNT,current_account);
//                    pageResult.setData(current_account);
//                } else {
//                    return PageResult.fixedError(ERRORCODE.WRONG_PASSWORD);
//                }
//            } else {
//                return PageResult.fixedError(ERRORCODE.WRONG_USERNAME);
//            }
//        } catch (Exception e) {
//            LOG.error(e.getMessage(), e);
//            return PageResult.fixedError(ERRORCODE.COMMON);
//        }
        return pageResult;
    }
}
