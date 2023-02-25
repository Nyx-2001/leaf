package com.leaf.dto;

import lombok.Data;

/**
 * @author Hanami
 * @Date 2023-02-21
 */
@Data
public class LoginFormDto {
    private String phone;
    private String code;
    private String password;
}
