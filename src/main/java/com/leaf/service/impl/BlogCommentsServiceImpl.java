package com.leaf.service.impl;

import com.leaf.dto.Result;
import com.leaf.entity.BlogComments;
import com.leaf.mapper.BlogCommentRepository;
import com.leaf.mapper.BlogCommentsMapper;
import com.leaf.service.IBlogCommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hanami
 * @since 2023-02-21
 */
@Service
public class BlogCommentsServiceImpl implements IBlogCommentsService {
    @Resource
    private BlogCommentRepository blogCommentRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public Result saveComment(BlogComments comments) {
        blogCommentRepository.save(comments);
        return Result.ok("评论成功!");
    }

    @Override
    public Result findAllComment() {
        List<BlogComments> commentsList = blogCommentRepository.findAll();
        return Result.ok(commentsList);
    }

    @Override
    public Result findCommentById(String id) {
        Optional<BlogComments> comments = blogCommentRepository.findById(id);
        return Result.ok(comments);
    }

    @Override
    public Result deleteCommentById(String id) {
        blogCommentRepository.deleteById(id);
        return Result.ok("删除成功!");
    }

    @Override
    public Result findCommentsByBlogId(String blogId) {
        Query query = Query.query(Criteria.where("blogid").is(blogId));
        List<BlogComments> blogCommentsList = mongoTemplate.find(query, BlogComments.class, "blogcomment");
        return Result.ok(blogCommentsList);
    }

    @Override
    public Result updateComment(BlogComments comments) {
        blogCommentRepository.save(comments);
        return Result.ok("评论修改成功!");
    }
}
