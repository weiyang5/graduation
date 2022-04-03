package com.graduation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tb_menu
 */
@TableName(value ="tb_menu")
@Data
public class Menu implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String icon;

    /**
     * 
     */
    private String href;

    /**
     * 
     */
    private String perms;

    /**
     * 
     */
    private Integer parentId;

    /**
     * 0管理员|1企业|2学生
     */
    private Integer type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}