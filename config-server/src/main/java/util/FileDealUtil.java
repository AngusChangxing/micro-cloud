package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * @class FileDealUtil
 * @brief 系统设置-本地文件处理
 *
 * 包括对配置文件进行动态修改。
 * @author 常星
 * @date 2017年7月21日
 */

public class FileDealUtil {

    private String a;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDealUtil.class);

    private static int once = 0;

    /**
     * @fn create Config File
     * @brief 对配置文件进行初始化
     * @author 常星
     * @date 2017年7月21日
     */
    public FileDealUtil(String configpath, String filename) {
        try {
            this.LOGGER.info("配置中心路径：" + configpath);
            this.LOGGER.info("配置中心文件：" + filename);
            //判断是否有文件夹，若无，则创建文件夹

            File dir = new File(configpath);
            if (!dir.exists() && !dir.isDirectory()){
                dir.setWritable(true, false);//赋予权限
                dir.mkdirs();//创建文件夹
            }

            //判断是否存在文件
            File file = new File(configpath + "/" + filename);//新建文件对象
            if(file.exists()){//查找文件，判断是否存在
                file.delete();
            }
            file.createNewFile();//若文件无，则进行创建
        } catch (IOException e) {
            this.LOGGER.error("创建文件有误-----------------------------------");
            e.printStackTrace();
        }
        this.modifyConfig(configpath, filename);
    }

    /**
     * @fn change config file
     * @brief 对配置文件进行初始化修改
     * @author 常星
     * @date 2017年7月21日
     */
    public void modifyConfig(String configpath, String filename){
        try {
            RandomAccessFile raf = new RandomAccessFile(configpath + "/" + filename, "rw");//创建读取文件对象
            StringBuffer buffer = new StringBuffer();//创建文字缓存对象
            //写入初始内容
            //String ipstr = IpUtil.localIP.replace(".","");
            //buffer.append("spring.profiles=eureka").append(ipstr);
            //buffer.append(System.getProperty("line.separator"));
            //buffer.append("eureka.instance.hostname=eureka").append(ipstr);
            //buffer.append(System.getProperty("line.separator"));
            //buffer.append("info=eureka").append(ipstr);
            //buffer.append(System.getProperty("line.separator"));
            //buffer.append("eureka.client.serviceUrl.defaultZone=http://").append(IpUtil.localIP).append(":8761/eureka/");
            buffer.append("eureka.client.serviceUrl.defaultZone=http://").append("127.0.0.1").append(":8761/eureka/");
            buffer.append(System.getProperty("line.separator"));

            raf.writeBytes(buffer.toString());//将数据写入
            raf.close();//关闭对象
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @fn change hosts files
     * @brief 对配置文件进行初始化修改
     * @author 常星
     * @date 2017年7月21日
     */
    public void modifyhost() {
        BufferedReader reader = null;//声明读取对象
        String line = null;
        StringBuffer buffer = new StringBuffer();
        RandomAccessFile rafout = null;
        String ipstr = IpUtil.getServerIp();
        try {
            reader = new BufferedReader(new FileReader("/etc/hosts"));//创建读取对象
            while ((line = reader.readLine()) != null) {//判定是否为空
                if (line.startsWith((ipstr + "\teureka"+ ipstr.replace(".", "")))){//判定是都以该字符串开头
                    FileDealUtil.once = 1;
                }
            }
            //若已有则不添加，无则进行添加
            if (FileDealUtil.once == 0){
                rafout = new RandomAccessFile("/etc/hosts","rw");
                long len = rafout.length();
                rafout.seek(len);
                //192.168.1.1 eureka192168111
                //对ip地址进行解析
                String str = "" + ipstr + "\teureka" + ipstr.replace(".", "");
                //写入文件
                rafout.writeBytes("\n" + new String(str.getBytes(),"iso8859-1"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if (rafout != null) {
                    rafout.close();
                    rafout = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
