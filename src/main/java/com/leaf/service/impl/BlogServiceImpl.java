package com.leaf.service.impl;

import com.leaf.entity.Blog;
import com.leaf.mapper.BlogMapper;
import com.leaf.service.IBlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

}
