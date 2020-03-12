package com.coding.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User{

    @TableId(type=IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //策略1：数据库自动填充
    //策略2：code自动填充
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    private Integer version;

}
