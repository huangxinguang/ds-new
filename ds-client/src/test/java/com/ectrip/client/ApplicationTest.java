package com.ectrip.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by huangxinguang on 2017/7/25 下午2:13.
 * 客户端启动入口
 */
public class ApplicationTest {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "classpath:applicationContext*.xml" });
        context.start();
        System.out.println("服务启动成功!");
        System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
    }
}
