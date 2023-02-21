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

    Result sendCode(String phone, HttpSession session);

    Result login(LoginFormDto loginForm, HttpSession session);

    Result sign();

    Result signCount();
}
