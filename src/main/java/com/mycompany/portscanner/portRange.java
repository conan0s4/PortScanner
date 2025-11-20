package com.mycompany.portscanner;


public class portRange {
    
    
    
    
    
    // Optional: scan a range of ports
    public static void tcpRange(String target, int startPort, int endPort) {
        System.out.println("Scanning " + target + " from port " + startPort + " to " + endPort + "...");
        for (int port = startPort; port <= endPort; port++) {
            TcpScanner scanner = new TcpScanner(target, port);
            if (scanner.scanPort()) {
                System.out.println("Port " + port + " is OPEN");
            }
        }
    }
    
  
    
       //scan huge range of ports if user hasn't specified specific port 
    public static void udpRange(String target, int startPort, int endPort) {
        System.out.println("UDP Scanning " + target + " from port " + startPort + " to " + endPort + "...");
//      
        for (int port = startPort; port <= endPort; port++) {//itterate from starting point to endport
            UdpScanner scanner = new UdpScanner(target, port);//scan port per ittiration
            String result = scanner.scanPort();
            if (!result.contains("CLOSED")) {
                System.out.println("UDP Port " + port + " : " + result);
            }
        }   
    
 
    
    
    
    } 
}
