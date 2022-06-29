package com.fax.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("nocv_global")
@Data
public class NocvGlobal {
    private String name;
    private Integer value;
}
