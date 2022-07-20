package com.fax.vo;

import com.fax.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDataVo extends Menu {
    private Integer size = 1;
    private Integer limit = 10;
}
