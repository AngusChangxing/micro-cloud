package com.cloud.socket;

import java.io.IOException;
import java.net.*;

/**
 * Created by New World on 2017/7/21.
 */
public class UdpSocket {
    private DatagramSocket socket = null;
    private DatagramPacket packet = null;
    private String ip = null;
    private String port = null;

    public UdpSocket() {
        try {
            this.socket = new DatagramSocket();
            byte[] pool = new byte[4096];
            this.packet = new DatagramPacket(pool, pool.length);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public UdpSocket(String port) {

        try {
            this.socket = new DatagramSocket(Integer.parseInt(port));
            byte[] pool = new byte[4096];
            this.packet = new DatagramPacket(pool, pool.length);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public UdpSocket(String ip, String port) {
        try {
            this.socket = new DatagramSocket();
            byte[] pool = new byte[4096];
            this.packet = new DatagramPacket(pool, pool.length,
                    InetAddress.getByName(ip), Integer.parseInt(port));
        } catch (SocketException e) {
            System.out.println("UdpSocet:SocketException异常");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void send(String message) {
        this.packet.setData(message.getBytes());
        try {
            this.socket.send(this.packet);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String receive() {
        String message = null;
        try {
            this.socket.receive(this.packet);
            message = new String(this.packet.getData());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return message;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public DatagramPacket getPacket() {
        return packet;
    }
}
