package com.fax.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Integer sex;
    private Integer age;
    private String phone;
    private String address;
    private String img;

    private Integer xueyuanId;
    private Integer banjiId;
    private Integer teacherId;

    //非数据库列，但是需要临时用一下
    @TableField(exist = false)
    private String banjiName;
    @TableField(exist = false)
    private String xueyuanName;
}
