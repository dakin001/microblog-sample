package com.example.moikiitos.repository.mybatis.mapper;

import com.example.moikiitos.account.model.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM `user` WHERE name = #{name}")
    Account findByName(@Param("name") String name);

    @Select("SELECT * FROM `user` WHERE email = #{email}")
    Account findByEmail(@Param("email") String email);

    @Insert("""
            INSERT INTO `user` (name, email, password, password_salt)
            values (#{name}, #{email}, #{password}, #{passwordSalt})
            """)
    void insert(Account account);
}
