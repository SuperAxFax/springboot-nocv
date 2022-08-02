package com.fax.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("vaccine")
public class Vaccine {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String card;
    private String phone;
    private String changjia;
    private String zhenci;
    private String checktype;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
}
