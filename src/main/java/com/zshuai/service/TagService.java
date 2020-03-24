package com.zshuai.service;

import com.zshuai.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 2:18 PM
 * @Version 1.0
 **/
public interface TagService {
    List<Tag> listTagTop(Integer size);

    /**
     * 查询所有标签
     * @return
     */
    List<Tag> listTag();
    List<Tag> listTag(String ids);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Tag> listTag(Pageable pageable);

    Tag getTagByName(String name);

    Tag saveTag(Tag tag);

    /**
     * 单个查询
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 修改
     * @param id
     * @param tag
     * @return
     */
    Tag updateTag(Long id, Tag tag);

    /**
     * 删除
     * @param id
     */
    void deleteTag(Long id);


}
