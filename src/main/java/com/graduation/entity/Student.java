package com.graduation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName tb_student
 */
@TableName(value ="tb_student")
@Data
public class Student implements Serializable {
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
    private String account;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Date birthday;

    /**
     * 
     */
    private String special;

    /**
     * 
     */
    private String college;

    /**
     * 
     */
    private String education;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private Integer sex;

    /**
     * 
     */
    private String photo;

    /**
     * 
     */
    private Date graduateDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}