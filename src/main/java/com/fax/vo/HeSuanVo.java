package com.fax.vo;

import com.fax.entity.HeSuan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeSuanVo extends HeSuan {
    private Integer size = 1;
    private Integer limit = 10;
}
