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
 * @TableName tb_experience
 */
@TableName(value ="tb_experience")
@Data
public class Experience implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String company;

    /**
     * 
     */
    private String post;

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
    private Date joinDate;

    /**
     * 
     */
    private Date leaveDate;

    /**
     * 
     */
    private Integer resumeId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}