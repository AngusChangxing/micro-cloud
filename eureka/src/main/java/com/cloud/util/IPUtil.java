package com.cloud.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by New World on 2017/5/28.
 */
public class IPUtil {
    public static String localIP = IPUtil.getServerIp();

    public IPUtil() {
    }

    public static boolean isWindowsOS(){
        boolean isWindows = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindows = true;
        }
        return isWindows;
    }

    //获取linux系统中的ip地址
    public static String getServerIp(){
        String ip = "";
        if(IPUtil.isWindowsOS()){
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
        //System.out.println("IP:"+ip);
        return ip;
    }
}
