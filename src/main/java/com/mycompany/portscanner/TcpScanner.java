package com.mycompany.portscanner;

import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.IOException;

public class TcpScanner {

    private String target;//ip address
    private int port;
    private int timeout = 200; // milliseconds //wait response for 200 milisec before confirming no response

    public TcpScanner(String target, int port) {
        this.target = target;
        this.port = port;
    }

    public boolean scanPort1() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(target, port), timeout);
            return true; // Port is open
        } catch (IOException e) {
            return false; // Port is closed or filtered
        }
    }

  
}
