package com.fax.vo;

import com.fax.entity.BanJi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BanJiVo extends BanJi {
    private Integer size = 1;
    private Integer limit = 10;
}
