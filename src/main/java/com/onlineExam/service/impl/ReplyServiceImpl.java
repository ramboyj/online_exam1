package com.onlineExam.service.impl;

import com.onlineExam.dao.ReplyMapper;
import com.onlineExam.model.Reply;
import com.onlineExam.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public int addReply(Reply reply) {
        return replyMapper.insertReply(reply);
    }

    @Override
    public List<Reply> getReliesByPostId(int postId) {
        return replyMapper.getReliesByPostId(postId);
    }
}
