package com.zshuai.controller;

import com.zshuai.pojo.Tag;
import com.zshuai.pojo.Type;
import com.zshuai.service.BlogService;
import com.zshuai.service.TagService;
import com.zshuai.service.TypeService;
import com.zshuai.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 1:48 PM
 * @Version 1.0
 **/

@Controller
//@Api(value = "标签页面相关功能", description = "负责标签页面上各类信息的查询")
public class TagShowController {

    @Autowired
    BlogService blogServiceImpl;
    @Autowired
    TagService tagServiceImpl;


    //@ApiOperation(httpMethod = "GET", value = "获取标签页面上的具体分类信息以及每个分类下对应的文章")
    @GetMapping("/tags/{id}")
    public String tag(Model model, @PageableDefault(size = 8, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id) {
        List<Tag> tags = tagServiceImpl.listTagTop(10000);
        if (id == -1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogServiceImpl.listBlog(id,pageable));
        model.addAttribute("avtiveTagId",id);
        return "tags";

    }

}
