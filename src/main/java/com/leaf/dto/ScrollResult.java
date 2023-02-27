package com.leaf.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author Hanami
 * @Date 2023-02-23
 */
@Data
public class ScrollResult {
    private List<?> list;
    private Long minTime;
    private Integer offset;
}
