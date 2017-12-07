package util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @class IpUtil
 * @brief 系统设置-ip地址获取
 *
 * 包括对本地ip地址的获取
 * @author 常星
 * @date 2017年7月21日
 */
public class IpUtil {
    public static String localIP = IpUtil.getServerIp();

    public static boolean isWindowsOS(){
        boolean isWindows = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindows = true;
        }
        return isWindows;
    }

    //获取系统中的ip地址(分windows和linux)
    public static String getServerIp(){
        String ip = "";
        if(IpUtil.isWindowsOS()){
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }else{
            try {
                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                    NetworkInterface intf = en.nextElement();
                    String name = intf.getName();
                    if (!name.contains("docker") && !name.contains("lo")) {
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress()) {
                                String ipaddress = inetAddress.getHostAddress().toString();
                                if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                    ip = ipaddress;
                                    //System.out.println(ipaddress);
                                }
                            }
                        }
                    }
                }
            } catch (SocketException ex) {
                System.out.println("获取ip地址异常");
                ip = "127.0.0.1";
                ex.printStackTrace();
            }
        }
        System.out.println("IP:"+ip);
        return ip;
    }

}
