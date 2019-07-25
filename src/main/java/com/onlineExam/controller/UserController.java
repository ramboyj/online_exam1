package com.onlineExam.controller;

import com.onlineExam.common.QexzConst;
import com.onlineExam.dto.PageResult;
import com.onlineExam.exception.ERRORCODE;
import com.onlineExam.model.User;
import com.onlineExam.service.UserService;
import com.onlineExam.service.impl.UserServiceImpl;
import com.onlineExam.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * create by zdk on 19/7/23
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static Log LOG = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;
//    @Autowired
//    private PostService postService;
//    @Autowired
//    private GradeService gradeService;
//    @Autowired
//    private ContestService contestService;
//    @Autowired
//    private SubjectService subjectService;
//
//    /**
//     * 个人信息页面
//     */
//    @RequestMapping(value="/profile", method= RequestMethod.GET)
//    public String profile(HttpServletRequest request, Model model) {
//        User currentUser = (User) request.getSession().getAttribute(QexzConst.CURRENT_ACCOUNT);
//        if (currentUser == null){
//            return "redirect:/";
//        }
//        model.addAttribute(QexzConst.CURRENT_ACCOUNT, currentUser);
//        return "/user/profile";
//    }
//
//
//    /**
//     * 考试记录页面
//     */
//    @RequestMapping(value="/myExam", method= RequestMethod.GET)
//    public String myExam(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
//        User currentUser = (User) request.getSession().getAttribute(QexzConst.CURRENT_ACCOUNT);
//        if (currentUser == null) {
//            return "redirect:/";
//        }
//        Map<String, Object> data = gradeService.getGradesByStudentId(page, QexzConst.gradePageSize, currentUser.getId());
//        List<Grade> grades = (List<Grade>) data.get("grades");
//        Set<Integer> contestIds = grades.stream().map(Grade::getContestId).collect(Collectors.toCollection(HashSet::new));
//        List<Contest> contests = contestService.getContestsByContestIds(contestIds);
//        List<Subject> subjects = subjectService.getSubjects();
//        Map<Integer, String> subjectId2name = subjects.stream().
//                collect(Collectors.toMap(Subject::getId, Subject::getName));
//        for (Contest contest : contests) {
//            contest.setSubjectName(subjectId2name.
//                    getOrDefault(contest.getSubjectId(), "未知科目"));
//        }
//        Map<Integer, Contest> id2contest = contests.stream().
//                collect(Collectors.toMap(Contest::getId, contest -> contest));
//        for (Grade grade : grades) {
//            grade.setContest(id2contest.get(grade.getContestId()));
//        }
//        model.addAttribute(QexzConst.DATA, data);
//        model.addAttribute(QexzConst.CURRENT_ACCOUNT, currentUser);
//        return "/user/myExam";
//    }
//
    /**
     * 更新密码
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public PageResult updatePassword(HttpServletRequest request) {
        PageResult pageResult = new PageResult();
        try {
            String oldPassword = request.getParameter("password");
            String newPassword = request.getParameter("newPassword");
            String jobNo = request.getParameter("jobNo");
            pageResult = userService.changePassword(jobNo,oldPassword,newPassword);
//            if (StringUtils.isNotEmpty(newPassword) && StringUtils.isNotEmpty(confirmNewPassword)
//                    && !newPassword.equals(confirmNewPassword)) {
//                return PageResult.fixedError(ERRORCODE.NOT_EQUALS_CONFIRM_PASSWORD);
//            }
//            User currentUser = (User) request.getSession().getAttribute(QexzConst.CURRENT_ACCOUNT);
//            if (!currentUser.getPassword().equals(md5OldPassword)) {
//                return PageResult.fixedError(ERRORCODE.WRONG_PASSWORD);
//            }
//            currentUser.setPassword(md5NewPassword);
//            //boolean result = userService.updateAccount(currentUser);
//            //pageResult.setSuccess(result);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return PageResult.fixedError(ERRORCODE.COMMON);
        }
        return pageResult;
    }
//
//    /**
//     * 更新个人信息
//     */
//    @RequestMapping(value = "/api/updateAccount", method = RequestMethod.POST)
//    @ResponseBody
//    public PageResult updateAccount(HttpServletRequest request, HttpServletResponse response) {
//        PageResult pageResult = new PageResult();
//        try {
//            String phone = request.getParameter("phone");
//            String qq = request.getParameter("qq");
//            String email = request.getParameter("email");
//            String description = request.getParameter("description");
//            String avatarImgUrl = request.getParameter("avatarImgUrl");
//            User currentUser = (User) request.getSession().getAttribute(QexzConst.CURRENT_ACCOUNT);
//            currentUser.setPhone(phone);
//            currentUser.setQq(qq);
//            currentUser.setEmail(email);
//            currentUser.setDescription(description);
//            currentUser.setAvatarImgUrl(avatarImgUrl);
//            boolean result = userService.updateAccount(currentUser);
//            pageResult.setSuccess(result);
//        } catch (Exception e) {
//            LOG.error(e.getMessage(), e);
//            return PageResult.fixedError(ERRORCODE.COMMON);
//        }
//        return pageResult;
//    }
//
    /**
     * 验证登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public PageResult login(HttpServletRequest request) {
            try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            return userService.validateUser(username,password);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return PageResult.fixedError(ERRORCODE.COMMON);
        }
    }

//    /**
//     * 用户退出
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/logout", method= RequestMethod.GET)
//    public String logout(HttpServletRequest request) {
//        request.getSession().setAttribute(QexzConst.CURRENT_ACCOUNT,null);
//        String url=request.getHeader("Referer");
//        LOG.info("url = " + url);
//        return "redirect:"+url;
//    }
//
//    /**
//     * API:添加用户
//     */
//    @RequestMapping(value="/api/addAccount", method= RequestMethod.POST)
//    @ResponseBody
//    public PageResult addAccount(@RequestBody User user) {
//        PageResult pageResult = new PageResult();
//        User existUser = userService.getAccountByUsername(user.getUsername());
//        if(existUser == null) {//检测该用户是否已经注册
//            user.setPassword(MD5.md5(QexzConst.MD5_SALT+ user.getPassword()));
//            user.setAvatarImgUrl(QexzConst.DEFAULT_AVATAR_IMG_URL);
//            user.setDescription("");
//            int accountId = userService.addAccount(user);
//            return new PageResult().setData(accountId);
//        }
//        return PageResult.fixedError(ERRORCODE.AREADY_EXIST_USERNAME);
//    }
//
//    /**
//     * API:更新用户
//     */
//    @RequestMapping(value="/api/updateManegeAccount", method= RequestMethod.POST)
//    @ResponseBody
//    public PageResult updateAccount(@RequestBody User user) {
//        PageResult pageResult = new PageResult();
//        user.setPassword(MD5.md5(QexzConst.MD5_SALT+ user.getPassword()));
//        boolean result = userService.updateAccount(user);
//        return new PageResult().setData(result);
//    }
//
//    /**
//     * API:删除用户
//     */
//    @DeleteMapping("/api/deleteAccount/{id}")
//    @ResponseBody
//    public PageResult deleteAccount(@PathVariable int id) {
//        PageResult pageResult = new PageResult();
//        boolean result = userService.deleteAccount(id);
//        return new PageResult().setData(result);
//    }
//
//    /**
//     * API:禁用账号
//     */
//    @RequestMapping(value="/api/disabledAccount/{id}", method= RequestMethod.POST)
//    @ResponseBody
//    public PageResult disabledAccount(@PathVariable int id) {
//        PageResult pageResult = new PageResult();
//        boolean result = userService.disabledAccount(id);
//        return new PageResult().setData(result);
//    }
//
//    /**
//     * API:解禁账号
//     */
//    @RequestMapping(value="/api/abledAccount/{id}", method= RequestMethod.POST)
//    @ResponseBody
//    public PageResult abledAccount(@PathVariable int id) {
//        PageResult pageResult = new PageResult();
//        boolean result = userService.abledAccount(id);
//        return new PageResult().setData(result);
//    }


}
