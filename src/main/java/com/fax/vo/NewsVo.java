package com.fax.vo;

import com.fax.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsVo extends News {
    private Integer size = 1;
    private Integer limit = 10;
}
