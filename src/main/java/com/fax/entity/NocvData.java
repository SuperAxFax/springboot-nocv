package com.fax.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("nocv_data")
@Data
public class NocvData {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer value;
}
