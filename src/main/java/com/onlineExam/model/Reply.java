package com.onlineExam.model;

import lombok.Data;

import java.util.Date;
@Data
public class Reply {

    private int id;

    private int userId;

    private int atuserId;

    private int postId;

    private int commentId;

    private String content;

    private Date createTime;

    private User user;

    private User atuser;

}
