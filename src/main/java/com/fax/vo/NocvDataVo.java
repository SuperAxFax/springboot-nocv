package com.fax.vo;

import com.fax.entity.NocvData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NocvDataVo extends NocvData {
    private Integer size = 1;
    private Integer limit = 10;
}
