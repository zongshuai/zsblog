package com.zshuai.controller;

import com.zshuai.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 4:55 PM
 * @Version 1.0
 **/
@Controller
//@Api(value = "归档页面", description = "按照年份整理博客信息")
public class ArchiveShowController {

    @Autowired
    BlogService blogServiceImpl;

    @GetMapping("/archives")
    //@ApiOperation(httpMethod = "GET", value = "以map形式查询传递博客信息，通过model传递给前端进行遍历")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogServiceImpl.archiveBlog());
        model.addAttribute("blogCount", blogServiceImpl.countBlog());
        return "archives";
    }
}
