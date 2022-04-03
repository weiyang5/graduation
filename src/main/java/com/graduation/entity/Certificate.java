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
 * @TableName tb_certificate
 */
@TableName(value ="tb_certificate")
@Data
public class Certificate implements Serializable {
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
    private String file;

    /**
     * 
     */
    private Date obtainDate;

    /**
     * 
     */
    private Integer resumeId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}