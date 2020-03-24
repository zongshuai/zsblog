package com.zshuai.service;



import com.zshuai.pojo.Comment;

import java.util.List;

/**
 * Created by zshuai
 *
 * @Date :2020/3/22 09:56 AM
 * @Version 1.0
 **/
public interface CommentService {

    /**
     * 通过id查询评论列表
     * @param blogId
     * @return
     */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 保存评论信息
     * @param comment
     * @return
     */
    Comment saveComment(Comment comment);

}
