package com.fax.vo;

import com.fax.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo extends Role {
    private Integer size = 1;
    private Integer limit = 10;
}
