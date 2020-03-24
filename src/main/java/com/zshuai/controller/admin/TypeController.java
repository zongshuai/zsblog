package com.zshuai.controller.admin;

import com.zshuai.pojo.Type;
import com.zshuai.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeServiceImpl;

    /**
     * 分页查询分类列表
     * @return
     */
    @GetMapping("/types")
    public String types(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)
                                Pageable pageable, Model model) {
        model.addAttribute("page",typeServiceImpl.listType(pageable));
        return "admin/types";
    }

    /**
     * 显示新增页面
     *
     * @return
     */
    @GetMapping("/types/input")
    public String showInput(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /**
     * 新增分类
     * @return
     */
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        //1.判断用户名是有重复
        Type type1 = typeServiceImpl.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        //2. 后端校验用户名是否为空
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type t = typeServiceImpl.saveType(type);
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    //跳转修改分类页面
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        model.addAttribute("type",typeServiceImpl.getType(id));
        return "admin/types-input";
    }


    //修改分类
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Type type1 = typeServiceImpl.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type t = typeServiceImpl.updateType(id,type);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    //删除分类
    @GetMapping("/types/{id}/delete")
    public String deleteTypeById(@PathVariable("id") Long id,RedirectAttributes attributes){
        Boolean tag = typeServiceImpl.deleteType(id);
        if (tag){
            attributes.addFlashAttribute("message","删除成功");
        }else{
            attributes.addFlashAttribute("message","该类型下有文章，不可删除");
        }

        return "redirect:/admin/types";
    }
}
