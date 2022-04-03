package com.graduation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tb_user_menu
 */
@TableName(value ="tb_user_menu")
@Data
public class UserMenu implements Serializable {
    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    @TableId
    private Integer menuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}