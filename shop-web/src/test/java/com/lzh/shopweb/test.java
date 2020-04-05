package com.lzh.shopweb;

import com.lzh.shopweb.facade.spiTest;

import java.util.ServiceLoader;

/**
 * java spi执行
 */
public class test {
    public static void main(String a[]){
        ServiceLoader<spiTest> serviceLoader  = ServiceLoader.load(spiTest.class);
        for(spiTest service : serviceLoader) {
            service.test();
        }
    }
}
