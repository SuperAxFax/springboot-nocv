package com.fax.vo;

import com.fax.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends User {
    private Integer size = 1;
    private Integer limit = 10;
}
