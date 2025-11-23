
package com.mycompany.portscanner;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.io.IOException;

public class UdpScanner {

    private String target;
    private int port;
    private int timeout = 500; // UDP needs a slightly longer timeout

    public UdpScanner(String target, int port) {
        this.target = target;
        this.port = port;
    }

    public String scanPort2() {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(timeout);

            byte[] sendData = "TEST".getBytes();
            InetAddress address = InetAddress.getByName(target);

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);

            byte[] recvBuf = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);

            socket.receive(receivePacket);

            return "OPEN (responded)";
        } 
        catch (SocketTimeoutException e) {
            return "OPEN | FILTERED (no response)";
        } 
        catch (IOException e) {
            return "CLOSED (ICMP unreachable)";
        }
    }

    }










