package com.example.moikiitos.infrastructure.repository.mybatis.mapper;

import com.example.moikiitos.domain.account.model.Account;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AccountMapper {

    @Select("SELECT id, name, email, password FROM `user` WHERE name = #{name}")
    Account findByName(@Param("name") String name);

    @Select("SELECT id, name, email, password FROM `user` WHERE email = #{email}")
    Account findByEmail(@Param("email") String email);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("""
            INSERT INTO `user` (name, email, password)
            values (#{name}, #{email}, #{password})
            """)
    void insert(Account account);
}
