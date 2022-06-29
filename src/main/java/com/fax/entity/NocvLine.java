package com.fax.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.xml.crypto.Data;

@lombok.Data
@TableName("nocv_line")
public class NocvLine {
    private Integer id;
    private Integer confirm;
    private Integer seperate;
    private Integer cure;
    private Integer dead;
    private Integer similar;
    private Data createtime;
}
