package com.onlineExam.controller;

import com.onlineExam.dto.PageResult;
import com.onlineExam.model.Post;
import com.onlineExam.service.PostService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    private static Log LOG = LogFactory.getLog(PostController.class);

    @Autowired
    private PostService postService;

    //添加帖子
    @RequestMapping(value="/api/addPost", method= RequestMethod.POST)
    @ResponseBody
    public PageResult addPost(@RequestBody Post post) {
        PageResult pageResult = new PageResult();
        int postId = postService.addPost(post);
        return new PageResult().setData(postId);
    }

    //更新帖子
    @RequestMapping(value="/api/updatePost", method= RequestMethod.POST)
    @ResponseBody
    public PageResult updatePost(@RequestBody Post post) {
        PageResult pageResult = new PageResult();
        boolean result = postService.updatePostById(post);
        return new PageResult().setData(result);
    }

    //删除帖子
    @DeleteMapping("/api/deletePost/{id}")
    public PageResult deletePost(@PathVariable int id) {
        PageResult pageResult = new PageResult();
        boolean result = postService.deletePostById(id);
        return new PageResult().setData(result);
    }
}
