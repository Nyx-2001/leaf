package com.leaf.mapper;

import com.leaf.entity.BlogComments;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author Hanami
 * @Date 2023-02-28
 */
public interface BlogCommentRepository extends MongoRepository<BlogComments,String> {
}
