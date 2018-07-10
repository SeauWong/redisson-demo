package com.wongcu.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.wongcu.plugins.MybatisSQLLogInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@Configuration
@ConditionalOnClass(DataSource.class)
@MapperScan({"com.wongcu.**.orm.mapper"})
@EnableTransactionManagement
@Import(DruidDataSourceAutoConfigure.class)
public class MyBatisAutoConfig implements TransactionManagementConfigurer {

    private static final ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    @Autowired
    private DataSource dataSource;

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSource);
            bean.setTypeAliasesPackage(typeAliasesPackage);
            bean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));

            //添加分页和日志插件
            bean.setPlugins(new Interceptor[]{new MybatisSQLLogInterceptor()});


            bean.setMapperLocations(resolver.getResources(mapperLocations));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}