package com.onlineExam.controller;

import com.onlineExam.common.QexzConst;
import com.onlineExam.dto.PageResult;
import com.onlineExam.model.*;
import com.onlineExam.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/")
public class DefaultController {

    private static Log LOG = LogFactory.getLog(DefaultController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReplyService replyService;

    /**
     * 首页
     */
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String home(HttpServletRequest request, Model model) {
        User currentUser = (User) request.getSession().getAttribute(QexzConst.CURRENT_ACCOUNT);
        model.addAttribute(QexzConst.CURRENT_ACCOUNT, currentUser);
        return "/home";
    }

    /**
     * 在线考试列表页
     */
    @RequestMapping(value="/contest/index", method= RequestMethod.GET)
    public String contestIndex(HttpServletRequest request,
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               Model model) {
        contestService.updateStateToStart();
        contestService.updateStateToEnd();
        User currentUser = (User) request.getSession().getAttribute(QexzConst.CURRENT_ACCOUNT);
        model.addAttribute(QexzConst.CURRENT_ACCOUNT, currentUser);
        Map<String, Object> data = contestService.getContests(page, QexzConst.contestPageSize);
        model.addAttribute(QexzConst.DATA, data);
        return "/contest/index";
    }

    /**
     * 在线考试页
     */
    @RequestMapping(value="/contest/{contestId}", method= RequestMethod.GET)
    public String contestDetail(HttpServletRequest request,
                               @PathVariable("contestId") int contestId,
                               Model model) {
        User currentUser = (User) request.getSession().getAttribute(QexzConst.CURRENT_ACCOUNT);
        model.addAttribute(QexzConst.CURRENT_ACCOUNT, currentUser);
        Contest contest = contestService.getContestById(contestId);
        if (currentUser == null || contest.getStartTime().getTime() > System.currentTimeMillis()
                || contest.getEndTime().getTime() < System.currentTimeMillis()) {
            return "redirect:/contest/index";
        }
        List<Question> questions = questionService.getQuestionsByContestId(contestId);
        for (Question question : questions) {
            question.setAnswer("");
        }
        Map<String, Object> data = new HashMap<>();
        data.put("contest", contest);
        data.put("questions", questions);
        model.addAttribute(QexzConst.DATA, data);
        return "/contest/detail";
    }

    /**
     * 题库中心页
     */
    @RequestMapping(value="/problemset/list", method= RequestMethod.GET)
    public String problemSet(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        User currentUser = (User) request.getSession().getAttribute(QexzConst.CURRENT_ACCOUNT);
        Map<String, Object> data = subjectService.getSubjects(page, QexzConst.subjectPageSize);

        model.addAttribute(QexzConst.CURRENT_ACCOUNT, currentUser);
        model.addAttribute(QexzConst.DATA, data);
        return "/problem/problemset";
    }

    /**
     * 题目列表页
     */
    @RequestMapping(value="/problemset/{problemsetId}/problems", method= RequestMethod.GET)
    public String problemList(HttpServletRequest request,
                              @PathVariable("problemsetId") Integer problemsetId,
                              @RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "content", defaultValue = "") String content,
                              @RequestParam(value = "difficulty", defaultValue = "0") int difficulty,
                              Model model) {
        User currentUser = (User) request.getSession().getAttribute(QexzConst.CURRENT_ACCOUNT);
        Map<String, Object> data = questionService.getQuestionsByProblemsetIdAndContentAndDiffculty(page, QexzConst.questionPageSize,
                problemsetId, content, difficulty);
        Subject subject = subjectService.getSubjectById(problemsetId);
        data.put("subject", subject);
        model.addAttribute(QexzConst.CURRENT_ACCOUNT, currentUser);
        model.addAttribute(QexzConst.DATA, data);
        return "/problem/problemlist";
    }

    /**
     * 题目详情页
     */
    @RequestMapping(value="/problemset/{problemsetId}/problem/{problemId}", method= RequestMethod.GET)
    public String problemDetail(HttpServletRequest request,
                                @PathVariable("problemsetId") Integer problemsetId,
                                @PathVariable("problemId") Integer problemId,
                                Model model) {
        User currentUser = (User) request.getSession().getAttribute(QexzConst.CURRENT_ACCOUNT);
        Map<String, Object> data = new HashMap<>();
        Question question = questionService.getQuestionById(problemId);
        Subject subject = subjectService.getSubjectById(problemsetId);
        data.put("question", question);
        data.put("subject", subject);
        model.addAttribute(QexzConst.CURRENT_ACCOUNT, currentUser);
        model.addAttribute(QexzConst.DATA, data);
        return "/problem/problemdetail";
    }


    /**
     * 测试分布式一致性session
     * @param session
     * @return
     */
    @RequestMapping(value = "/uid", method = RequestMethod.GET)
    @ResponseBody
    public PageResult uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return new PageResult().setData(session.getId());
    }
}
