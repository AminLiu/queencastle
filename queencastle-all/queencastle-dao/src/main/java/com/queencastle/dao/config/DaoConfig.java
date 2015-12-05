package com.queencastle.dao.config;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.queencastle.dao.mapper.MapperScanner;
import com.queencastle.dao.mybatis.IdTypeHandler;

@Configuration
@MapperScan(basePackageClasses = MapperScanner.class)
public class DaoConfig {
    private static Logger logger = LoggerFactory.getLogger(DaoConfig.class);

    /** 配置文件路径 */
    public static final String MAPPING_LOCATION = "classpath:/mapper/**/*.xml";

    public static final String PROFILE_NAME_DEV = "dev";
    public static final String PROFILE_NAME_PROD = "prod";

    @Value("${jdbc.url}")
    private String jdbc_url;
    @Value("${jdbc.username}")
    private String jdbc_user;
    @Value("${jdbc.password}")
    private String jdbc_password;

    @Value("${profiles.active}")
    private String PROFILES_ACTIVE;

    @Profile(PROFILE_NAME_DEV)
    @Bean(name = "propertyPlaceholderConfigurer")
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurerDev() {
        PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
        ClassPathResource resource = new ClassPathResource("env/config-dev.properties");
        ppc.setLocation(resource);
        logger.info("env/config-dev.properties loaded");
        return ppc;
    }


    @Profile(PROFILE_NAME_PROD)
    @Bean(name = "propertyPlaceholderConfigurer")
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurerProd() {
        PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
        ClassPathResource resource = new ClassPathResource("env/config-prod.properties");
        ppc.setLocation(resource);
        logger.info("env/config-prod.properties loaded");
        return ppc;
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(jdbc_url);
        dataSource.setUsername(jdbc_user);
        dataSource.setPassword(jdbc_password);
        return dataSource;
    }



    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() throws SQLException {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setTypeAliases(new Class<?>[] {IdTypeHandler.class});
        Resource[] mapperLocations = getMapperLocations();
        sessionFactoryBean.setMapperLocations(mapperLocations);
        return sessionFactoryBean.getObject();
    }

    public Resource[] getMapperLocations() throws IOException {
        logger.info("资源文件加载...");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(MAPPING_LOCATION);
        return resources;
    }
}
