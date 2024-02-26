package com.example.moikiitos.infrastructure.repository.mybatis.mapper;

import com.example.moikiitos.domain.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    // replace "*" to columns
    @Select("SELECT * FROM `user` WHERE id = #{id}")
    User findById(@Param("id") Long id);

    @Select("SELECT * FROM `user` WHERE name = #{name}")
    User findByName(@Param("name") String name);

    @Select("SELECT * FROM `user` WHERE email = #{email}")
    User findByEmail(@Param("email") String email);

    @Select("<script>" +
            "SELECT id, name, email FROM `user` WHERE id IN " +
            "<foreach item='item' index='index' collection='ids'" +
            " open='(' separator=',' close=')'>" +
            " #{item}" +
            "</foreach>" +
            "</script>")
    List<User> findByIds(@Param("ids") List<Long> ids);
}
