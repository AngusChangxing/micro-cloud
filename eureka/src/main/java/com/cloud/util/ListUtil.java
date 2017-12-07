package com.cloud.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @class ListUtil
 * @brief 系统工具类
 * 用于提供系统接受数据信息的list集合
 *
 * @author 常星
 * @date 2017年8月9日
 * @version 1.0
 */
public class ListUtil {
    //用于存放所有接受到的eureka高可用注册信息
    public static List<String> infoList = new ArrayList<String>();

    //用于存储未处理的eureka注册信息
    public static List<String> undealList = new ArrayList<String>();

    //用于零时存放某一时刻的未处理的eureka注册信息
    public static List<String> middleList = new ArrayList<String>();

    //用于存放已经写入配置文件的eureka，注册信息
    public static List<String> dealList = new ArrayList<String>();
}
