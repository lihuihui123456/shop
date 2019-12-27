package com.lzh.shopdao.dao.writeDataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Auther: lzh
 * @Date: 2019/12/27 17:31
 * @Description:
 */
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = "com.lzh.shopdao.mapper", sqlSessionFactoryRef = "writeSqlSessionFactory")

public class WriteDataSourceConfig {
    /**
     * @Bean 注册Bean对象
     * @Primary 表示默认数据源
     * @ConfigurationProperties 读取properties中的配置参数映射成为一个对象
     */
    @Bean(name = "writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public HikariDataSource getSqlserverDateSource() {
        return new HikariDataSource();
    }

    /**
     * @param datasource 数据源
     * @return SqlSessionFactory
     * @Primary 默认SqlSessionFactory
     */
    @Bean(name = "writeSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlserverSqlSessionFactory(@Qualifier("writeDataSource") DataSource datasource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        //mybatis扫描xml所在位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/*.xml"));
        bean.setTypeAliasesPackage("com.lzh.shopentity");
        return bean.getObject();
    }

    @Bean("writeSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlserverSqlSessionTemplate(@Qualifier("writeSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
