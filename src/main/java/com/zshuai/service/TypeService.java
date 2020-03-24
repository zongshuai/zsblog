package com.zshuai.service;

import com.zshuai.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 2:04 PM
 * @Version 1.0
 **/
public interface TypeService {


    //查询前size个标签
    List<Type> listTypesTop(Integer size);

    List<Type> listType();

    /**
     * 单个查询
     * @param id
     * @return
     */
    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type getTypeByName(String name);

    Type saveType(Type type);

    Type updateType(Long id, Type type);

    Boolean deleteType(Long id);
}
