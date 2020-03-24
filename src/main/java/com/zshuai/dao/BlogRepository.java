package com.zshuai.dao;

import com.zshuai.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 12:26 PM
 * @Version 1.0
 **/

//JpaSpecificationExecutor实现JPA的动态条件查询
public interface BlogRepository  extends JpaRepository<Blog,Long>,JpaSpecificationExecutor<Blog>{

    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views= b.views+1 where b.id = ?1")
    int updateViews(Long id);

    //@Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    @Query(value = "SELECT date_format(b.update_time, '%Y' ) AS YEAR FROM t_blog as b " +
            "GROUP BY date_format(b.update_time, '%Y') ORDER BY YEAR DESC", nativeQuery = true)
    List<String> findGroupYear();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

    @Query( value = "SELECT COUNT(type_id) from t_blog where type_id = ?1", nativeQuery = true)
    int findByTypeId(Long id);
}
