package com.graduation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tb_intention
 */
@TableName(value ="tb_intention")
@Data
public class Intention implements Serializable {
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
    private String salary;

    /**
     * 
     */
    private String city;

    /**
     * 
     */
    private String type;

    /**
     * 
     */
    private String industry;

    /**
     * 
     */
    private Integer resumeId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}