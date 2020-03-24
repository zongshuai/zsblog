package com.zshuai.controller.admin;

import com.zshuai.pojo.Blog;
import com.zshuai.pojo.User;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;


/**
 * Created by zshuai
 *
 * @Date :2020/3/19 1:48 PM
 * @Version 1.0
 **/
@Controller
@RequestMapping("admin")
//@Api(value = "博客管理", description = "博客管理页面的相关操作")
public class BlogController {
    @Autowired
    BlogService blogServiceImpl;

    @Autowired
    TypeService typeServiceImpl;

    @Autowired
    TagService tagServiceImpl;

    @GetMapping("/blogs")
    //@ApiOperation(httpMethod = "GET", value = "博客管理页面的数据显示")
    public String showBlogs(Model model, @PageableDefault(size = 8, sort = "updateTime",
            direction = Sort.Direction.DESC)Pageable pageable, BlogQuery blog){
        //博客列表
        model.addAttribute("page", blogServiceImpl.listBlog(pageable, blog));
        //分类列表
        model.addAttribute("types",typeServiceImpl.listType());
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    //@ApiOperation(httpMethod = "POST", value = "博客管理页面的博客搜索数据")
    public String searchBlogs(Model model, @PageableDefault(size = 8, sort = "updateTime",
            direction = Sort.Direction.DESC)Pageable pageable, BlogQuery blog){
        //博客列表
        model.addAttribute("page", blogServiceImpl.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }



    @GetMapping("/blogs/input")
    //@ApiOperation(httpMethod = "GET", value = "博客管理页面的新增博客")
    public String addBlogs(Model model){
        setTypeAadTag(model);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    //@ApiOperation(httpMethod = "POST", value = "博客管理页面的博提交客")
    public String postBlog(Blog blog, RedirectAttributes attributes , HttpSession session){
        System.out.println("123"+blog.toString());
        if(blog.getFlag() == null || blog.getFlag() == ""){
            System.out.println("进来了");
            blog.setFlag("原创");
        }
        System.out.println("flag:"+blog.getFlag());
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeServiceImpl.getType(blog.getType().getId()));
        blog.setTags(tagServiceImpl.listTag(blog.getTagIds()));
        Blog b;
        Date date = new Date();
        //新增博客l
        if(blog.getId() == null){
            blog.setUpdateTime(date);
            blog.setCreateTime(date);
            blog.setViews(0);
            b = blogServiceImpl.saveBlog(blog);
        } else {//更新博客
            b = blogServiceImpl.updateBlog(blog.getId(),blog);
        }
        if (b==null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/blogs";
    }

    //修改博客
    @GetMapping("/blogs/{id}/input")
    public String editBlog(@PathVariable("id") Long id, Model model){
        setTypeAadTag(model);
        Blog blog = blogServiceImpl.getBlog(id);
        //处理tagIds
        blog.init();
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }

    //删除博客
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable("id") Long id){
        blogServiceImpl.deleteBlog(id);
        return "redirect:/admin/blogs";
    }





    private void setTypeAadTag(Model model){
        /*博客查询,为后面的编辑博客做准备*/
        model.addAttribute("blog", new Blog());
        /*分类查询*/
        model.addAttribute("types", typeServiceImpl.listType());
        /*标签查询*/
        model.addAttribute("tags",tagServiceImpl.listTag());
    }

}
