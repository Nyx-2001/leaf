package com.leaf.controller;


import com.leaf.dto.Result;
import com.leaf.entity.BlogComments;
import com.leaf.service.IBlogCommentsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hanami
 * @since 2023-02-21
 */
@RestController
@RequestMapping("/blog-comments")
public class BlogCommentsController {

    @Resource
    private IBlogCommentsService blogCommentsService;

    /**
     * 发表评论
     * @param blogComments
     * @return
     */
    @PostMapping
    public Result saveComment(@RequestBody BlogComments blogComments) {
        return blogCommentsService.saveComment(blogComments);
    }

    /**
     * 查询一个blog下的评论
     * @param blogId
     * @return
     */
    @GetMapping("/{blogId}")
    public Result findCommentsByBlogId(@PathVariable("blogId") Long blogId) {
        String id = String.valueOf(blogId);
        return blogCommentsService.findCommentsByBlogId(id);
    }

//    /**
//     * 根据id查询评论
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}")
//    public Result findCommentById(@PathVariable("id") String id) {
//        return blogCommentsService.findCommentById(id);
//    }

    /**
     * 更新评论
     * @param blogComments
     * @return
     */
    @PutMapping
    public Result updateComment(@RequestBody BlogComments blogComments) {
        return blogCommentsService.updateComment(blogComments);
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteCommentById(@PathVariable("id") String id) {
        return blogCommentsService.deleteCommentById(id);
    }


}
