package com.cloud.application;

import org.springframework.beans.factory.annotation.Value;

import util.FileDealUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import util.IpUtil;

/**
 * @class ConfigServerApplication
 * @brief 系统启动类
 *
 * 包含对配置文件的初始化
 * @author 常星
 * @date 2017年7月21日
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {


    public static void main( String[] args ) {
        if (IpUtil.isWindowsOS()) {
            FileDealUtil winFileDeal = new FileDealUtil("e:/testconfig","eureka-pro.properties");
        }else {
            FileDealUtil linuxFileDeal = new FileDealUtil("/home/pi/configfile", "eureka-pro.properties");
        }
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
