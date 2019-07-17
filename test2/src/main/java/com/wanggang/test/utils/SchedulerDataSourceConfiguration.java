package com.wanggang.test.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class SchedulerDataSourceConfiguration {

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String dbUser;
    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Primary
    @Bean(name = "schedulerDataSource")
    public DataSource schedulerDataSource() throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setLoginTimeout(3000);
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(50);
        dataSource.setMaxWait(2000);
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("select 1");
        dataSource.setTimeBetweenEvictionRunsMillis(150000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setConnectionProperties("connectTimeout=1000;socketTimeout=12000");
        dataSource.setName("scheduler");
        dataSource.init();
        return dataSource;
    }

    @Bean(name = "schedulerSqlSessionFactory")
    @Primary
    public SqlSessionFactory schedulerSqlSessionFactory(@Qualifier("schedulerDataSource") DataSource schedulerDataSource)
        throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(schedulerDataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "schedulerTxManager")
    @Primary
    public PlatformTransactionManager schedulerTransactionManager(@Qualifier("schedulerDataSource") DataSource schedulerDataSource) {
        return new DataSourceTransactionManager(schedulerDataSource);
    }

}
