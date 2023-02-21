package com.leaf.service.impl;

import com.leaf.entity.BlogComments;
import com.leaf.mapper.BlogCommentsMapper;
import com.leaf.service.IBlogCommentsService;
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
public class BlogCommentsServiceImpl extends ServiceImpl<BlogCommentsMapper, BlogComments> implements IBlogCommentsService {

}
