package com.mycompany.portscanner;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/*custom data structure type: singly linked list 
what data it stores: pattern service signatures
Singly linked list – Each node points to the next node only
*/
class ServiceNode { 
    String pattern; //will be used to be compared at 
    String serviceName; //just data with pattern
    String software; //--> value – stores the data for this node
    ServiceNode next; //next – holds a reference to the next node in the list
    /*chain of nodes head node1 node2 node3-->null(pointer)
    [nod1: p sn sw next-->][nod2: p sn sw next-->][nod3: p sn sw next-->]   
    arrange/accessed-checked in sequential order
    
    */

    //used when creating a new node (this method gets called) in the add service function
    public ServiceNode(String pattern, String serviceName, String software) { //constructor
        //values added --> assign to
        //this (refers to the var set in service node ) differentiates which is a local from a method parameter if name is the same 
        this.pattern = pattern;
        this.serviceName = serviceName;
        this.software = software;
        this.next = null;
        //new node contains: p sn sw null --> to store value added to there respected var
        
    }
}

public class ServiceDetector {

    private ServiceNode head;//refers to the first nod

    public ServiceDetector() {  
        head = null; //making sure linkllist starts empty
        loadDefaultSignatures(); // then load up the predefined signatures
    }

    private void loadDefaultSignatures() { // dont return any value 
        //only accessible inside the servicedetecetor class
        addService("SSH-", "SSH", "OpenSSH");
        addService("HTTP", "HTTP", "Apache");
        addService("FTP", "FTP", "FileZilla");
        addService("SMTP", "SMTP", "Mail Server");
        addService("MySQL", "MySQL", "Database");
        addService("TLS", "HTTPS", "Secure Web Server");
        addService("Server: Apache", "HTTP", "Apache");
        addService("nginx", "HTTP", "Nginx");
        addService("IIS", "HTTP", "Microsoft IIS");  
        addService("SMB", "SMB", "Windows File Sharing");
        addService("NT LM 0.12", "SMB", "Windows SMB");
        addService("Docker", "Docker", "Container Service");
        // add more signatures here: 
    }
//only accessible within this classno return value
    private void addService(String pattern, String serviceName, String software) { // function to create a new service node
        ServiceNode newNode = new ServiceNode(pattern, serviceName, software);
//head : first nodeof a linked list 
//if empty no nodes then add a new node 
        if (head == null) head = newNode;
        else { //if node has already existing nodes then
            //from the head (start of node) ittirate to the end of the list 
            //if value is not null then proceed to the next until  you reach theend which is if its null or no value
            ServiceNode temp = head;
            while (temp.next != null) temp = temp.next;//here we store the nods checked ifempty or not and if  empty then its
            //stops then that should be the last value
            temp.next = newNode; //link current last node to the new node (gets added at the end)
        }
    }

    public String identifyService(String ip, int port) {
        String banner = grabBanner(ip, port); //call grabbanner here to beused to identiy service

        if (banner == null) return "Unknown service"; //if it reurns null or equals to null then return then prompt user

        ServiceNode match = matchBanner(banner); //match banner we got from grabbanner function if it returned a value
        if (match != null) { //if returned a value then 
            return match.serviceName + " (" + match.software + ")"; //if patttern match then get its servicename/software
        }
        return "Unknown service";
    }

    private String grabBanner(String ip, int port) { //connect to service  and read its response
        try (Socket socket = new Socket(ip, port)) { //tcp connect to to target ip /port
            socket.setSoTimeout(1500); // wait for this time period for server/client response
            //prevents it from endlessly waiting so wait for a specific time period

            OutputStream out = socket.getOutputStream(); //allows sendingdata
            InputStream in = socket.getInputStream(); //allows reading data
            out.write("\n".getBytes()); //sends a newline to the server

            byte[] buffer = new byte[128];// reads upto 128 bytes/char from target
            int len = in.read(buffer);//stores the read buffer to len (number of bytes read
            //if has some value or greater than 0 then --->
            if (len > 0) return new String(buffer, 0, len); //return this value
            /*Converts the byte array to a string containing the banner.
              Only returns if some data was received.*/

        } catch (Exception e) {
            // No banner = silent fails
            //error handler
        }
        return null; //if no response or error return null 
    }
//traverse linked list from start to end
    private ServiceNode matchBanner(String banner) { // loop through the list node by node until it reached the end or equals to null
        ServiceNode current = head; 
        while (current != null) {
            if (banner.contains(current.pattern)) return current; //if banner contains current pattern  (foreach node)
            //return the node   if match
            current = current.next;//if no match move to the next  
        }
        return null; //if no pattern match then return null
    }
}
