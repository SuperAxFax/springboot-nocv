package com.fax.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.crypto.Data;
import java.util.Date;

@TableName("nocv_apidata")
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class NocvApiData {
    @TableId(value = "id",  type = IdType.AUTO)
    private Integer id;
    private Integer confirm;
    private Integer input;
    private Integer severe;
    private Integer heal;
    private Integer dead;
    private Integer suspect;
    //这个错误就tm离谱，日期的数据类型是Date，而不是Data
    private Date datatime;
}
