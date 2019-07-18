package com.wanggang.test.quartz.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
@PropertySource("classpath:/quartz.properties")
public class SchedulerDataSourceConfiguration {

    @Value("${org.quartz.dataSource.qzDS.URL}")
    private String dbUrl;
    @Value("${org.quartz.dataSource.qzDS.user}")
    private String dbUser;
    @Value("${org.quartz.dataSource.qzDS.password}")
    private String dbPassword;
    @Value("${org.quartz.dataSource.qzDS.driver}")
    private String driver;

    @Primary
    @Bean(name = "schedulerDataSource")
    public DataSource schedulerDataSource() throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
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
