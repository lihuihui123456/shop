package com.lzh.shopweb;

import com.lzh.shopweb.facade.spiTest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ServiceLoader;

@SpringBootApplication
    @ComponentScan(basePackages = {"com"})   //spring管理的bean
    @EnableAutoConfiguration
    //@MapperScan("com.lzh.shop.mapper")//mybatis 配置扫描
    public class ShopWebApplication {
        public static void main(String[] args) throws InterruptedException {
            SpringApplication.run(ShopWebApplication.class, args);
            Iterator<spiTest> iterator = ServiceLoader.load(spiTest.class).iterator();
            //开始时间
            //提示用户输入
            Date begin=new Date();
            Thread.sleep(5000);
            Date end=new Date();
            try {
                //计算时间差
                long diff = end.getTime() - begin.getTime();
                //计算天数
                long days = diff / (1000 * 60 * 60 * 24);
                //计算小时
                long hours = (diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                //计算分钟
                long minutes = (diff % (1000 * 60 * 60)) / (1000 * 60);
                //计算秒
                long seconds = (diff % (1000 * 60)) / 1000;
                //输出
                System.out.println("你输入的日期时间差："+days+"天"+hours+"小时"+minutes+"分"+seconds+"秒");
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("你输入的日期格式不对，请重新输入！");
            }
        }
    }