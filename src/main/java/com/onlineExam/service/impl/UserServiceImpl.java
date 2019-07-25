package com.onlineExam.service.impl;

import com.alibaba.fastjson.JSON;
import com.onlineExam.common.QexzConst;
import com.onlineExam.dao.UserMapper;
import com.onlineExam.dto.PageResult;
import com.onlineExam.exception.ERRORCODE;
import com.onlineExam.model.User;
import com.onlineExam.model.example.UserExample;
import com.onlineExam.service.UserService;
import com.onlineExam.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult validateUser(String jobNo, String password) {
        PageResult pageResult = new PageResult();
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andJobNoEqualTo(jobNo);
        List<User> user;
        try {
            user = userMapper.selectByExample(example);
        }catch (Exception e){
            return PageResult.fixedError(ERRORCODE.COMMON);
        }
        if (user.size() == 1){
            if (StringUtils.equals(MD5.md5(QexzConst.MD5_SALT + password),user.get(0).getPassword())){
                pageResult.setData(user);
                return pageResult;
            }else {
                return PageResult.fixedError(ERRORCODE.WRONG_PASSWORD);
            }
        }
        return PageResult.fixedError(ERRORCODE.WRONG_USERNAME_OR_PASSWORD);
    }

    @Override
    public PageResult changePassword(String jobNo, String password, String newPassowrd) {
        PageResult pageResult = new PageResult();
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andJobNoEqualTo(jobNo);
        User user = userMapper.selectByExample(example).get(0);
        UserExample example1 = new UserExample();
        UserExample.Criteria criteria1 = example.createCriteria();
        criteria1.andPasswordEqualTo(MD5.md5(QexzConst.MD5_SALT + newPassowrd));
        try {
            int i = userMapper.updateByExample(user,example1);
            if (i == 1){
                return pageResult;
            }
        }catch (Exception e){
            return PageResult.fixedError(ERRORCODE.COMMON);
        }
        return PageResult.fixedError(ERRORCODE.WRONG_USERNAME_OR_PASSWORD);
    }

    //    @Override
//    public int addAccount(User user) {
//        user.setAvatarImgUrl(QexzConst.DEFAULT_AVATAR_IMG_URL);
//        return userMapper.insertAccount(user);
//    }
//
//    @Override
//    public boolean updateAccount(User user) {
//        return userMapper.updateAccountById(user) > 0;
//    }
//
//    @Override
//    public boolean updateAvatarImgUrlById(String avatarImgUrl, int id) {
//        return userMapper.updateAvatarImgUrlById(avatarImgUrl, id) > 0;
//    }
//
//    @Override
//    public User getAccountByUsername(String username) {
//        return userMapper.getAccountByUsername(username);
//    }
//
//    @Override
//    public List<User> getAccountsByStudentIds(List<Integer> studentIds) {
//        return userMapper.getAccountsByIds(studentIds);
//    }
//
//    @Override
//    public Map<String, Object> getAccounts(int pageNum, int pageSize) {
//        Map<String, Object> data = new HashMap<>();
//        int count = userMapper.getCount();
//        if (count == 0) {
//            data.put("pageNum", 0);
//            data.put("pageSize", 0);
//            data.put("totalPageNum", 1);
//            data.put("totalPageSize", 0);
//            data.put("accounts", new ArrayList<>());
//            return data;
//        }
//        int totalPageNum = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
//        if (pageNum > totalPageNum) {
//            data.put("pageNum", 0);
//            data.put("pageSize", 0);
//            data.put("totalPageNum", totalPageNum);
//            data.put("totalPageSize", 0);
//            data.put("accounts", new ArrayList<>());
//            return data;
//        }
//        PageHelper.startPage(pageNum, pageSize);
//        List<User> users = userMapper.getAccounts();
//        data.put("pageNum", pageNum);
//        data.put("pageSize", pageSize);
//        data.put("totalPageNum", totalPageNum);
//        data.put("totalPageSize", count);
//        data.put("accounts", users);
//        return data;
//    }
//
//    @Override
//    public boolean deleteAccount(int id) {
//        return userMapper.deleteAccount(id) > 0;
//    }
//
//    @Override
//    public boolean disabledAccount(int id) {
//        return userMapper.updateState(id, 1) > 0;
//    }
//
//    @Override
//    public boolean abledAccount(int id) {
//        return userMapper.updateState(id, 0) > 0;
//    }
//
//    @Override
//    public List<User> getAccountsByIds(Set<Integer> ids) {
//        return userMapper.getAccountsByIdSets(ids);
//    }
//
//    @Override
//    public User getAccountById(int id) {
//        return userMapper.getAccountById(id);
//    }
}
