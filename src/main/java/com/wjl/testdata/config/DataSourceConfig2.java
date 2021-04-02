package com.wjl.testdata.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: DataSourceConfig2
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/1/21   WangJiLin     Create the current class
 *************************************************************************
 ******/
//@Configuration
//@MapperScan(basePackages = "com.example.multipledatasource.mapper.db2", sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DataSourceConfig2 {

//    @Bean("db2DataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.test2")
//    public DataSource getDb1DataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean("db2SqlSessionFactory")
//    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/db2/*.xml"));
//        return bean.getObject();
//    }
//
//    @Bean("db2SqlSessionTemplate")
//    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("db1SqlSessionTemplate") SqlSessionFactory sqlSessionFactory){
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
}