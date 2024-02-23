package com.example.moikiitos.repository.mybatis.mapper;

import com.example.moikiitos.shared.PageQuery;
import com.example.moikiitos.user.model.Follower;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FollowerMapper {
    @Insert("""
            INSERT INTO `Follower` (follower_id, following_id)
            values (#{follower.id}, #{following.id})
            """)
    void insert(Follower follower);

    @Delete("""
            DELETE FROM `Follower` 
            WHERE follower_id = #{follower.id} and following_id = #{following.id}
            """)
    void remove(Follower follower);

    @Select("""
            SELECT 1 FROM `Follower` 
            WHERE follower_id = #{follower.id} and following_id = #{following.id} limit 1
            """
    )
    Optional<Boolean> isExists(Follower follower);

    @Select("""
            SELECT FOLLOWER_ID as id FROM `FOLLOWER`
            WHERE FOLLOWING_ID = #{userId} limit #{page.limit}
            """
    )
    List<Long> listFollowers(@Param("userId") Long userId, @Param("page") PageQuery page);

    @Select("""
            SELECT FOLLOWING_ID as id FROM `FOLLOWER` 
            WHERE FOLLOWER_ID = #{userId} limit #{page.limit}
            """
    )
    List<Long> listFollowing(@Param("userId") Long userId, @Param("page") PageQuery page);
}
