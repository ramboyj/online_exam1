package com.onlineExam.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class Grade {

    private int id;

    private int studentId;

    private int contestId;

    private int result;

    private int autoResult;

    private int manulResult;

    private String answerJson;

    private Date createTime;

    private Date finishTime;

    private int state;

    private User student;

    private Contest contest;

    private List<Question> questions;

}
