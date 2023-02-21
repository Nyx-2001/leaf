package com.leaf.service.impl;

import com.leaf.entity.UserInfo;
import com.leaf.mapper.UserInfoMapper;
import com.leaf.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hanami
 * @since 2023-02-21
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
