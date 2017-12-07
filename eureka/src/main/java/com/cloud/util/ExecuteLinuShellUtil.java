package com.cloud.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by New World on 2017/5/30.
 */
public class ExecuteLinuShellUtil {
    public static String execute(){
        InputStream in = null;
        String result = "";
        try {
            if (DebugModeUtil.isDebug == 1) {
                System.out.println("开始执行刷新命令");
            }
            Process pro = Runtime.getRuntime().exec("curl -X POST http://"+ IPUtil.localIP + ":8761/refresh");
            pro.waitFor();//等待命令执行完才会执行下一条
            in = pro.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            result = read.readLine();
            System.out.println("INFO:"+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
