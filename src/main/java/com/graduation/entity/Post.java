package com.graduation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tb_post
 */
@TableName(value ="tb_post")
@Data
public class Post implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String education;

    /**
     * 
     */
    private String salary;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Integer companyId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}