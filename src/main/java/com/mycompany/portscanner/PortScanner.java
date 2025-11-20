package com.mycompany.portscanner;

import picocli.CommandLine;          // Core class used to run/execute the CLI application
import picocli.CommandLine.Command;  // Annotation to mark a class as a command (CLI entry point)
import picocli.CommandLine.Option;   // Annotation to define command‑line options like -s, -p, etc.

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;


/*[@]commands section*/
/*----------------------------------------------*/
@Command(name = "portscanner") 
public class PortScanner implements Runnable {

    @Option(names = "-s")
    private String address;

    @Option(names = "-p")
    private Integer port;

    @Option(names = "-t")
    private boolean tcpMode;

    @Option(names = "-u")
    private boolean udpMode;
  


/*[@]funtions section*/
/*----------------------------------------------*/    
    @Override
    public void run() {
        
        
        
            // If no args. given then prompt user
    if (!tcpMode && !udpMode && port == null && address == null) {
                System.out.println("""
                
                PORT SCANNER TOOL
                           
                Usage: portscanner [-t] [-u] [-p <PORT>] -s <IP>
                             -s <IP>         Target IP address to scan (required for scanning).
                             -p <PORT>       Port number to scan (1-65535). Default uses scan mode only.
                             -t              Enable TCP scan mode.
                             -u              Enable UDP scan mode.           
                
                by: Conan0s4
                """);
                
                return;
    
    
    }
    
    //if -s arg. has no value then return
     //if -s arg. has value then
    //validatestore into var
    if (address == null){return;}else if (address != null) {String addr = address;}
    //if -p arg.  has value then
    //store into var                     //if no -p arg. then scan all ports
    if (port != null){int p = port;}else { boolean scanall = true;}    
     

       //if either tcp/udp hasn't been specified then  use the defualt scanner which is tcp
    if (!tcpMode && !udpMode){
        tcpMode = true;
    }
    
    else if (tcpMode) {
    if (port != null) { //if port is specified : use value inputted by the user 
        TcpScanner scanner1 = new TcpScanner(address, port);
        if (scanner1.scanPort()) { //if port is scanned then prompt user
            System.out.println("address: " + address);
            System.out.println("Port " + port + " is OPEN");
        } else {  //if close still prompt user
            System.out.println("address: " + address);
            System.out.println("Port " + port + " is CLOSED");
        }
        ScanTime.printFinishedTime(); //unclude exact time scanned
    
    } else { //if no ports specified use this range of ports
        portRange.tcpRange(address, 1, 65535);
        System.out.println("address: " + address);
        ScanTime.printFinishedTime();

    }
}


    //if -u then use udp mod scanner
    else if (udpMode) {
            if (port != null) { // if port args has value then      
        UdpScanner scanner2 = new UdpScanner(address, port);
        String result = scanner2.scanPort();

        System.out.println("address: " + address);

        if (result.contains("responded")) {
            System.out.println("Port " + port + " is OPEN");
        } else if (result.contains("CLOSED")) {
            System.out.println("Port " + port + " is CLOSED");
        } else {
            System.out.println("Port " + port + " is OPEN | FILTERED");
        }

        ScanTime.printFinishedTime();

    } else { // if no port specified, scan full range
        portRange.udpRange(address, 1, 65535);//fom ---> to thos port number
        //using scanRange method/function : use the values declared
        
        System.out.println("address: " + address);
        ScanTime.printFinishedTime();
        
    }
        

                

                

                

        
        

    
        
        
    }
    
    
    }
    


/*[@]main class*/
/*----------------------------------------------*/
    public static void main(String[] args) {


//---------------> scanner commands function gets called in the main class
        new CommandLine(new PortScanner()).execute(args);

    }
}
