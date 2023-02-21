package com.leaf.dto;

import lombok.Data;

/**
 * @author starsofocean
 * date 2023/2/21 15:53
 */
@Data
public class LoginFormDto {
    private String phone;
    private String code;
    private String password;
}
