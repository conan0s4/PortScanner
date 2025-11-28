# PortScanner
<h2>Port Scanner Tool</h2>

<h3>Features</h3>
<ul>
  <li><strong>TCP Scan Mode:</strong> Attempts a full connection to check port status and grab banners.</li>
  <li><strong>UDP Scan Mode:</strong> Sends test packets to detect open or filtered UDP ports.</li>
  <li><strong>Custom Data Structure:</strong> Uses a singly linked list to store and process scanned services efficiently.</li>
  <li><strong>Picocli Integration:</strong> Clean and structured terminal commands with built-in help options.</li>
</ul>

<h3>Installation (Kali Linux / Any OS with Java)</h3>
<ol>
  <li>Ensure Java is installed:
    <pre><code>java -version</code></pre>
  </li>
  <li>Download the <code>.jar</code> release file.</li>
  <li>Run it in the terminal:
    <pre><code>java -jar portscanner.jar --help</code></pre>
  </li>
</ol>

<h3>Usage</h3>
<pre><code>
PORT SCANNER TOOL

Usage: portscanner [-t] [-u] [-p &lt;PORT&gt;] -s &lt;IP&gt;

  -s &lt;IP&gt;       Target IP address to scan (required)
  -p &lt;PORT&gt;     Port number to scan (1–65535). Optional if scan mode only.
  -t            Enable TCP scan mode
  -u            Enable UDP scan mode

Examples:
  java -jar portscanner.jar -s 192.168.1.10 -t
  java -jar portscanner.jar -s 10.0.0.5 -u -p 53
  java -jar portscanner.jar --help
</code></pre>

<p><strong>Author:</strong> Conan0s4</p>

<p><em>For educational and authorized network security testing only.</em></p>

