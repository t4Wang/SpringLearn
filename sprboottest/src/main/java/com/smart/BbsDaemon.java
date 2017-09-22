package com.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class BbsDaemon {

    @RequestMapping
    public String index() {
        return "测试sitemap数据";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BbsDaemon.class, args);
    }
}
