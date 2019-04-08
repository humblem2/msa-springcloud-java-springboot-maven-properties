package com.example.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = "com.example", sqlSessionFactoryRef="getSqlSessionFactory") 
@EnableTransactionManagement
public class DatabaseConfig 
{
	/**
     * DataSource 빈을 등록 
     */
	@Bean
    @ConfigurationProperties(prefix="datasource")
    public PoolProperties getPoolProperties(){
        return new PoolProperties();
    }
	
    /**
     * SqlSessionFactory 빈을 등록 
     */
    @Bean
    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception 
    {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);													
        factory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        return factory.getObject();
    }
    
    /**
     * SqlSessionTemplate 빈을 등록 
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception 
    {
      final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
      return sqlSessionTemplate;
    }
}
