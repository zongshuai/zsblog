package com.zshuai.service;

import com.zshuai.pojo.Blog;
import com.zshuai.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 1:56 PM
 * @Version 1.0
 **/
public interface BlogService {

    /**
     * 分页查询博客列表
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Long tagId, Pageable pageable);

    /**
     * 模糊查询分页显示博客列表
     * @param pageable
     * @param query
     * @return
     */
    Page<Blog> listBlog(Pageable pageable, String query);

    //recommend根据是否推荐标签进行查询
    List<Blog> listRecommendBlogTop(Integer size);

    /**
     * 显示归档信息
     * @return
     */
    Map<String ,List<Blog>> archiveBlog();


    Long countBlog();

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    Blog getBlog(Long id);

    void deleteBlog(Long id);

    Blog getAadConvertBlog(Long id);
}
