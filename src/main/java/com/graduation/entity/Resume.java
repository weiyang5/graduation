package com.graduation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tb_resume
 */
@TableName(value ="tb_resume")
@Data
public class Resume implements Serializable {
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
    private String jobStatus;

    /**
     * 
     */
    private String evaluate;

    /**
     * 
     */
    private String skill;

    /**
     * 
     */
    private Integer studentId;

    /**
     * 0关闭|1开放
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}