package com.leaf.mapper;

import com.leaf.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Hanami
 * @since 2023-02-21
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

}
