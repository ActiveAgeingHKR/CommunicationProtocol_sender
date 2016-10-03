/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicationprotocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Sender {
    Socket socket = null;
    ObjectOutputStream out = null;
    BufferedReader in = null;

    public void send() {
        establishContact();
        
        try {
            Customer elder = new Customer();
            elder.setName("börje");
            elder.setPulse(50);
            out.writeObject(elder);
            System.out.println(elder);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        closeContact();
    }

    private void establishContact() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 12345); //InetAddress.getLocalHost()
            out = new ObjectOutputStream(socket.getOutputStream()); 
            in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O");
            System.exit(1);
        }
    }

    private void closeContact() {
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException ioe) {
            System.out.println("Failed");
            System.exit(-1);
        }
    }
}
