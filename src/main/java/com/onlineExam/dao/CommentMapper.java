package com.onlineExam.dao;

import com.onlineExam.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CommentMapper {

    int insertComment(@Param("comment") Comment comment);

    List<Comment> getCommentsByPostId(@Param("postId") int postId);

    int getCount();

    List<Comment> getComments();

    int deleteCommentById(@Param("id") int id);
}
