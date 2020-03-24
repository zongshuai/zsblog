package com.zshuai.vo;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BlogQuery {
    /**
     * 标题
     */
    private String title;
    /**
     * 分类ID
     */
    private Long typeId;
    /**
     * 是否推荐
     */
    private boolean recommend;

}
