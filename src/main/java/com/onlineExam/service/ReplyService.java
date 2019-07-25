package com.onlineExam.service;

import com.onlineExam.model.Reply;

import java.util.List;

public interface ReplyService {

    int addReply(Reply reply);

    List<Reply> getReliesByPostId(int postId);
}
