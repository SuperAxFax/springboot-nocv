package com.fax.vo;

import com.fax.entity.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineVo extends Vaccine {
    private Integer size = 1;
    private Integer limit = 10;
}
