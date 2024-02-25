package com.example.moikiitos.repository.mybatis.mapper;

import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.shared.PageQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Insert("""
            INSERT INTO `post` (user_id, content)
            values (#{user.id}, #{content})
            """)
    void insert(Post post);

    @Select("""
            SELECT a.id, a.USER_ID, a.CONTENT, a.create_at
            From(
            	SELECT a.id, a.USER_ID, a.CONTENT, a.create_at
            	FROM `Post` a
            	INNER JOIN `FOLLOWER` b ON b.FOLLOWING_ID = a.USER_ID
            	WHERE b.FOLLOWER_ID = #{userId}
            	UNION
            	SELECT a.id, a.USER_ID, a.CONTENT, a.create_at
            	FROM `Post` a
            	WHERE a.USER_ID = #{userId}
            ) a
            order by a.id desc
            limit #{page.skip}, #{page.limit}
            """
    )
    @Results({
            @Result(column = "user_id", property = "user.id"),
    })
    List<Post> findFeedByUserId(@Param("userId") Long userId, @Param("page") PageQuery page);
}
