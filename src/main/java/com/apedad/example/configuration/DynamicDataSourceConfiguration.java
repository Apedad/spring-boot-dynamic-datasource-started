package com.apedad.example.configuration;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.apedad.example.commons.DataSourceKey;
import com.apedad.example.commons.DynamicRoutingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RocLiu [apedad@qq.com]
 * @version 1.0
 */
@MapperScan(basePackages = "com.apedad.example.dao")
@Configuration
public class DynamicDataSourceConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "multiple.datasource.master")
    public DataSource dbMaster() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "multiple.datasource.slave1")
    public DataSource dbSlave1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "multiple.datasource.slave2")
    public DataSource dbSlave2() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "multiple.datasource.other")
    public DataSource dbOther() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 核心动态数据源
     *
     * @return 数据源实例
     */
    @Bean
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setDefaultTargetDataSource(dbMaster());
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        dataSourceMap.put(DataSourceKey.DB_MASTER, dbMaster());
        dataSourceMap.put(DataSourceKey.DB_SLAVE1, dbSlave1());
        dataSourceMap.put(DataSourceKey.DB_SLAVE2, dbSlave2());
        dataSourceMap.put(DataSourceKey.DB_OTHER, dbOther());
        dataSource.setTargetDataSources(dataSourceMap);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        //此处设置为了解决找不到mapper文件的问题
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    /**
     * 事务管理
     *
     * @return 事务管理实例
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
