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
 * @TableName tb_favor
 */
@TableName(value ="tb_favor")
@Data
public class Favor implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer companyId;

    /**
     * 
     */
    private Integer postId;

    /**
     * 
     */
    private Integer studentId;

    /**
     * 
     */
    private Date favorDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}