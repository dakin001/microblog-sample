package com.example.moikiitos.repository.mybatis.mapper;

import com.example.moikiitos.post.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    @Insert("""
            INSERT INTO `post` (user, content)
            values (#{user.id}, #{content})
            """)
    void insert(Post post);
}
