package com.example.moikiitos.repository.mybatis;

import com.example.moikiitos.repository.mybatis.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackageClasses = UserMapper.class)
public class MyBatisConfig {

}

