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
 * @TableName tb_channel
 */
@TableName(value ="tb_channel")
@Data
public class Channel implements Serializable {
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
    private Integer parentId;

    /**
     * 
     */
    private String channelImg;

    /**
     * 
     */
    private String summary;

    /**
     * Y单页|其他非单页
     */
    private String single;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Integer sort;

    /**
     * 
     */
    private Date createDate;

    /**
     * 
     */
    private Integer createUser;

    /**
     * D删除
     */
    private String deletedFlag;

    /**
     * 
     */
    private Integer postion;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}