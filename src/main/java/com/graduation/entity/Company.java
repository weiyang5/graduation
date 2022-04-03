package com.graduation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tb_company
 */
@TableName(value ="tb_company")
@Data
public class Company implements Serializable {
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
    private String contact;

    /**
     * 
     */
    private String telephone;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String addr;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private String size;

    /**
     * 
     */
    private String type;

    /**
     * 
     */
    private String logo;

    /**
     * 
     */
    private String description;

    /**
     * 0待审核|1审核通过|2审核拒绝
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}