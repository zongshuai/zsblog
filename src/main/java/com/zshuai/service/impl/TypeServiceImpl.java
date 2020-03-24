package com.zshuai.service.impl;

import com.zshuai.dao.BlogRepository;
import com.zshuai.dao.TypeRepository;
import com.zshuai.pojo.Type;
import com.zshuai.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 2:05 PM
 * @Version 1.0
 **/
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    BlogRepository blogRepository;

    /**
     * 按照分页方式查询前6个标签
     *
     * @param size
     * @return
     */
    public List<Type> listTypesTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0, size, sort);
        return typeRepository.findTop(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Transactional
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.findOne(id);
    }

    @Transactional
    @Override
    public Type saveType(Type type) {

        return typeRepository.save(type);
    }


    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findOne(id);
        if (t == null) {
            //throw new NotFoundException("不存在该类型");
            System.out.println("bucunza");
        }
        BeanUtils.copyProperties(type, t);
        return typeRepository.save(t);
    }

    @Override
    @Transactional
    public Boolean deleteType(Long id) {
        //判断blog中是否有引用的类型分类，没有才可删除
        int typeCount = blogRepository.findByTypeId(id);

        System.out.println("123333"+typeCount);
        if (typeCount != 0){
            return false;
        }else {

            typeRepository.delete(id);
            return true;
        }
    }

}
