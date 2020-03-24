package com.zshuai.dao;

import com.zshuai.pojo.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 13:34 PM
 * @Version 1.0
 **/
/**
 * 标签
 */
public interface TagRepository extends JpaRepository<Tag,Long>{
    Tag findByName(String name);


    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);

}
