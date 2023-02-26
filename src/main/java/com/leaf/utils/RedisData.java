package com.leaf.utils;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Hanami
 * @Date 2023-02-21
 */
@Data
public class RedisData {
    private LocalDateTime expireTime;
    private Object data;
}
