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
 * @TableName tb_send
 */
@TableName(value ="tb_send")
@Data
public class Send implements Serializable {
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
    private Integer resumeId;

    /**
     * 
     */
    private Date sendDate;

    /**
     * 0待查看|1已查看|2有意向|3不合适
     */
    private Integer status;

    /**
     * 
     */
    private Integer studentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}