package com.fax.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("nocv_pie")
public class NocvPie {
    private String name;
    private Integer value;
}
