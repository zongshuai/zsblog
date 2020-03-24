package com.zshuai.controller;

import com.zshuai.pojo.Blog;
import com.zshuai.service.BlogService;
import com.zshuai.service.TagService;
import com.zshuai.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 1:48 PM
 * @Version 1.0
 **/

@Controller
//@Api(value = "首页index相关功能", description = "负责页面上各类信息的查询返回")
public class IndexController {

    @Autowired
    BlogService blogServiceImpl;
    @Autowired
    TypeService typesServiceImpl;

    @Autowired
    TagService tagServiceImpl;


   // @ApiOperation(httpMethod = "GET", value = "获取首页上博客、分类、标签以及推荐博客的数据")
    @GetMapping("/")
    public String index(Model model, @PageableDefault(size = 8, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable) {
        //1.获取分页的博客列表
        model.addAttribute("page", blogServiceImpl.listBlog(pageable));
        //2.获取分类的内容（显示6 条）
        model.addAttribute("types", typesServiceImpl.listTypesTop(6));

        //3.获取标签的内容
        model.addAttribute("tags", tagServiceImpl.listTagTop(10));

        //4.获取推荐的博客列表
        //List<Blog> blogs = blogServiceImpl.listRecommendBlogTop(8);
        model.addAttribute("recommendBlogs", blogServiceImpl.listRecommendBlogTop(8));
        return "index";

    }

   // @ApiOperation(httpMethod = "GET", value = "获取底栏最新动态数据，只查询前三条，返回的是视图")
    @GetMapping("/footer/newblog")
    public String newblos(Model model) {
        model.addAttribute("newblogs", blogServiceImpl.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }


   // @ApiOperation(httpMethod = "POST", value = "根据搜索框中输入的信息进行相关博客标题或正文的模糊查询")
    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogServiceImpl.listBlog(pageable, "%"+query+"%"));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public  String blog(@PathVariable("id") Long id, Model model){
        model.addAttribute("blog",blogServiceImpl.getAadConvertBlog(id));
        return "blog";
    }




}
