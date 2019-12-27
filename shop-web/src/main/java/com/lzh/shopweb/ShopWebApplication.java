package com.lzh.shopweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
    @SpringBootApplication
    @ComponentScan(basePackages = {"com"})   //spring管理的bean
    @EnableAutoConfiguration
    //@MapperScan("com.lzh.shopdao.mapper")//mybatis 配置扫描
    public class ShopWebApplication {
        public static void main(String[] args) {
            SpringApplication.run(ShopWebApplication.class, args);
        }
    }