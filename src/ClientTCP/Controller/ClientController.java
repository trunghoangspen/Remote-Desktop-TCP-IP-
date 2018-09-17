/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientTCP.Controller;

import java.awt.Dimension;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trung Hoang
 */
public class ClientController {

    private Socket mySocket;
    private String host;
    private int port;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
   private Dimension d;
    public ClientController(String host, int port, Dimension d) {
        this.host = host;
        this.port = port;
     this.d = d;

    }

    public boolean initClient() {
        boolean b = connectServer();
        if (b == false) {
            return false;
        }
        SetScreen();
        return true;
    }

    public boolean connectServer() {
        try {
            mySocket = new Socket(host, port);
            System.out.println("Kết nối đến: " + host + " " + port);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public void sendFile(File f) {
        SendFile sf = new SendFile(oos, f.getPath());
        //File f = new File(patch);
        try {
            oos.writeObject(f.getName());
        } catch (IOException ex) {
           // System.out.println("Lỗi ở đây");
           // Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sf.start();
    }

    public void SetScreen() {
        try {
            oos = new ObjectOutputStream(mySocket.getOutputStream());
            ois = new ObjectInputStream(mySocket.getInputStream());
            oos.writeObject(d);
            //System.out.println("Đã tạo oos");
            SetScreen ss = new SetScreen(ois, oos);
            ss.start();
        } catch (IOException ex) {
           // Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
