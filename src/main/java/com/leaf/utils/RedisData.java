package com.leaf.utils;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author starsofocean
 * date 2023/2/21 18:07
 */
@Data
public class RedisData {
    private LocalDateTime expireTime;
    private Object data;
}
