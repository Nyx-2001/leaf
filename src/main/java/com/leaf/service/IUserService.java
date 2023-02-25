package com.leaf.service;

import com.leaf.dto.LoginFormDto;
import com.leaf.dto.Result;
import com.leaf.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hanami
 * @since 2023-02-21
 */
public interface IUserService extends IService<User> {

    Result sendCode(String phone);

    Result login(LoginFormDto loginForm);

    Result sign();

    Result signCount();
}
