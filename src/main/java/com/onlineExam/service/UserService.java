package com.onlineExam.service;

import com.onlineExam.dto.PageResult;

public interface UserService {

    PageResult validateUser(String jobNo, String password);

    PageResult changePassword(String jobNo,String password,String newPassowrd);
//    int addAccount(User user);
//
//    boolean updateAccount(User user);
//
//    boolean updateAvatarImgUrlById(String avatarImgUrl, int id);
//
//    User getAccountByUsername(String username);
//
//    List<User> getAccountsByStudentIds(List<Integer> studentIds);
//
//    Map<String, Object> getAccounts(int pageNum, int pageSize);
//
//    boolean deleteAccount(int id);
//
//    boolean disabledAccount(int id);
//
//    boolean abledAccount(int id);
//
//    List<User> getAccountsByIds(Set<Integer> ids);
//
//    User getAccountById(int id);
}
