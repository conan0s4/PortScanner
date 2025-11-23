package com.mycompany.portscanner;


public class portRange {
    
    /*function purpose: if user hasn't sepcified a port or rather used the -p arg(command then scan all ports from 
    the given range)*/
    
    
    // Optional: scan a range of ports
    public static void tcpRange(String target, int startPort, int endPort) {
        System.out.println("Scanning " + target + " from port " + startPort + " to " + endPort + "...");
        ServiceDetector detector = new ServiceDetector();
        for (int port = startPort; port <= endPort; port++) {
            TcpScanner scanner1 = new TcpScanner(target, port);
            if (scanner1.scanPort1()) {
                System.out.println("Port " + port + " is OPEN");
                String service = detector.identifyService(target, port);
                System.out.println("Service: " + service);
            }
        }
    }
    
  
    
       //scan huge range of ports if user hasn't specified specific port 
    public static void udpRange(String target, int startPort, int endPort) {
        System.out.println("UDP Scanning " + target + " from port " + startPort + " to " + endPort + "...");
//      
        for (int port = startPort; port <= endPort; port++) {//itterate from starting point to endport
            UdpScanner scanner2 = new UdpScanner(target, port);//scan port per ittiration
            String result = scanner2.scanPort2();
            if (!result.contains("CLOSED")) {
                System.out.println("UDP Port " + port + " : " + result);
            }
        }   
    
 
    
    
    
    } 
}
