package com.leaf.service;

import com.leaf.dto.Result;
import com.leaf.entity.BlogComments;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leaf.mapper.BlogCommentRepository;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hanami
 * @since 2023-02-21
 */
public interface IBlogCommentsService {
    Result saveComment(BlogComments comments);

    Result findAllComment();

    Result findCommentById(String id);

    Result deleteCommentById(String id);

    Result updateComment(BlogComments comments);

    Result findCommentsByBlogId(String blogId);


}
