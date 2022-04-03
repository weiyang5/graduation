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
 * @TableName tb_article
 */
@TableName(value ="tb_article")
@Data
public class Article implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer channelId;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String titleImg;

    /**
     * 
     */
    private String summary;

    /**
     * 
     */
    private String author;

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
     * 
     */
    private Date updateDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}