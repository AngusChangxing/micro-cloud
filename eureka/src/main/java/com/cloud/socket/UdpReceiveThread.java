package com.cloud.socket;

/**
 * Created by Linger on 2017/8/9.
 */
public class UdpReceiveThread extends Thread {
    private UdpSocket udpSocket;
    private String port;

    public UdpReceiveThread() {
    }

    public UdpReceiveThread(String port) {
        this.port = port;
        udpSocket = new UdpSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            String info = this.udpSocket.receive();

        }
    }
}
