package com.mycompany.portscanner;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class ServiceNode {
    String pattern;
    String serviceName;
    String software;
    ServiceNode next;

    public ServiceNode(String pattern, String serviceName, String software) {
        this.pattern = pattern;
        this.serviceName = serviceName;
        this.software = software;
        this.next = null;
    }
}

public class ServiceDetector {

    private ServiceNode head;

    public ServiceDetector() {
        head = null;
        loadDefaultSignatures();
    }

    private void loadDefaultSignatures() {
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
        // 📌 Add more anytime here — centralized and clean
    }

    private void addService(String pattern, String serviceName, String software) {
        ServiceNode newNode = new ServiceNode(pattern, serviceName, software);

        if (head == null) head = newNode;
        else {
            ServiceNode temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
    }

    public String identifyService(String ip, int port) {
        String banner = grabBanner(ip, port);

        if (banner == null) return "Unknown service";

        ServiceNode match = matchBanner(banner);
        if (match != null) {
            return match.serviceName + " (" + match.software + ")";
        }
        return "Unknown service";
    }

    private String grabBanner(String ip, int port) {
        try (Socket socket = new Socket(ip, port)) {
            socket.setSoTimeout(1500);

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            out.write("\n".getBytes());

            byte[] buffer = new byte[128];
            int len = in.read(buffer);

            if (len > 0) return new String(buffer, 0, len);

        } catch (Exception e) {
            // No banner = silent fail (normal for UDP or filtered ports)
        }
        return null;
    }

    private ServiceNode matchBanner(String banner) {
        ServiceNode current = head;
        while (current != null) {
            if (banner.contains(current.pattern)) return current;
            current = current.next;
        }
        return null;
    }
}
