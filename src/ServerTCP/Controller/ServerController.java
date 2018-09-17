/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerTCP.Controller;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trung Hoang
 */
public class ServerController extends Thread{
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket ClientSocket;
    private ServerSocket myServer;
  
    private int port;
    
    public ServerController(int port) {
        this.port = port;
        OpenServer();
    }
    
    public void OpenServer(){
        try {
            myServer = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    @Override
    public void run(){
        try {
            
            System.out.println("Server is running...");
            ClientSocket = myServer.accept();
            oos = new ObjectOutputStream(ClientSocket.getOutputStream());
            ois = new ObjectInputStream(ClientSocket.getInputStream());
          //  System.out.println("OK");
            Object o = ois.readObject();
            Dimension d = new Dimension(0, 0);
            if (o instanceof Dimension){
                d = (Dimension) o;
            }
            ReceiveEvent rc = new ReceiveEvent(ois, d);
            rc.start();
            CaptureScreen cs = new CaptureScreen(oos, d);
            cs.start();
            
        } catch (Exception ex) {
          //  Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
