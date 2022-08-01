package com.fax.vo;

import com.fax.entity.XueYuan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XueYuanVo extends XueYuan {
    private Integer size = 1;
    private Integer limit = 10;
}
