package com.zshuai.dao;

import com.zshuai.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 14:16 PM
 * @Version 1.0
 **/
public interface TypeRepository extends JpaRepository<Type,Long>{

    Type findByName(String name);


    /**
     * 查询type按照分页的方式
     * @param pageable
     * @return
     */
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);

}
