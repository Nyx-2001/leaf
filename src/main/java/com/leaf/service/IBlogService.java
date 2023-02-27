package com.leaf.service;

import com.leaf.dto.Result;
import com.leaf.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hanami
 * @since 2023-02-21
 */
public interface IBlogService extends IService<Blog> {

    Result saveBlog(Blog blog);

    Result queryHotBlog(Integer current);

    Result queryBlogById(Long id);

    Result likeBlog(Long id);

    Result queryBlogLikes(Long id);

    Result queryBlogOfFollow(Long max, Integer offset);

}
