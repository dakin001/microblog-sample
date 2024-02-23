package com.example.moikiitos.repository.mybatis;

import com.example.moikiitos.repository.mybatis.mapper.AccountMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackageClasses = AccountMapper.class)
public class MyBatisConfig {

}

