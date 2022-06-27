package com.fax.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("nocv_data")
@Data
public class NocvData {
    /*private Integer id;*/
    private String name;
    private Integer value;
}
