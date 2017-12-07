package com.cloud.socket;

/**
 * Created by Linger on 2017/8/9.
 */
public class UdpSendEurekaThread extends Thread {
    private UdpSocket udpSocket;
    private String ip;
    private String port;

    public UdpSendEurekaThread() {
    }

    public UdpSendEurekaThread(String ip, String port) {
        this.ip = ip;
        this.port = port;
        udpSocket = new UdpSocket(ip, port);
    }


}
