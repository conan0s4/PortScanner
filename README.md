# PortScanner
<h2>Port Scanner Tool</h2>

<p>
  A command‑line based port scanner built in Java using <strong>Picocli</strong> for argument handling.
  Supports both <strong>TCP</strong> and <strong>UDP</strong> scanning for local network testing and analysis.
</p>

<ul>
  <li>
    <strong>TCP & UDP Scan Modes:</strong> Choose between scanning TCP ports, UDP ports, or both.
  </li>
  <li>
    <strong>Banner Grabbing (TCP):</strong> Attempts to retrieve service information from open TCP ports
    to help identify what software or protocol is running.
  </li>
  <li>
    <strong>Educational Network Testing:</strong> Intended only for scanning systems you own or have permission to test.
  </li>
</ul>

<h3>Installation (Kali Linux & Other Linux Systems)</h3>
<ol>
  <li>Ensure Java JDK 8+ is installed:</li>
</ol>
<pre><code>sudo apt update
sudo apt install default-jdk
</code></pre>

<ol start="2">
  <li>Place the <code>portscanner.jar</code> file in your preferred directory.</li>
  <li>Run it using:</li>
</ol>

<pre><code>java -jar portscanner.jar [arguments]
</code></pre>

<h3>Usage</h3>
<pre><code>PORT SCANNER TOOL

Usage: portscanner [-t] [-u] [-p &lt;PORT&gt;] -s &lt;IP&gt;

  -s &lt;IP&gt;        Target IP address to scan (required)
  -p &lt;PORT&gt;      Port number to scan (1–65535)
  -t             Enable TCP scan mode
  -u             Enable UDP scan mode

Examples:
  java -jar portscanner.jar -s 192.168.1.10 -t
  java -jar portscanner.jar -s 10.0.0.5 -u -p 53
</code></pre>

<p><em>by: Conan0s4</em></p>

<p><strong>Disclaimer:</strong> This tool is for authorized network security testing and educational purposes only.</p>

