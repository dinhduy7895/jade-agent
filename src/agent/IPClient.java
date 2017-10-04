/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

/**
 *
 * @author bluerain
 */
public class IPClient {
    String IPAddress;
    String hostname;

    public String getIPAddress() {
        return IPAddress;
    }

    public String getHostname() {
        return hostname;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public IPClient(String IPAddress, String hostname) {
        this.IPAddress = IPAddress;
        this.hostname = hostname;
    }
    
}
