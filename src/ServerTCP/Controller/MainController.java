/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerTCP.Controller;

import ClientTCP.Views.ScreenForm;
import ServerRMI.Controller.RMIInterface;
import ServerTCP.Views.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.AppConfigurationEntry;

/**
 *
 * @author Trung Hoang
 */
public class MainController {

    //private String host = "localhost";
    private int port;
    private String myHost;
    private ServerController sc;
    private MainForm mf;
    
    private String rmiService = "RMIServer";
    private ClientTCP.Controller.ClientController clientContr;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    public MainController() {
        try {
            mf = new MainForm();
          
            this.port = getPort();
            this.myHost = getIP();
          
            mf.setMyIP(myHost);
            mf.setMyPort(this.port);
            mf.ConnectAction(new ConnectListenner());
            mf.SendFileAction(new SendFileListenner());
            mf.show();
            System.out.println("Mở kết nối: " + myHost + " " + port);
            sc = new ServerController(port);
            sc.start();
        } catch (Exception ex) {
            System.out.println("Lỗi Server");
        }
    }

    
     public String getIP() {
        try {
            InetAddress thisIp;

            thisIp = InetAddress.getLocalHost();
            // thisIp = InetAddress.getByName("Wireless");
            return (thisIp.getHostAddress());
            // InetAddress.getByName("localhost");
        } catch (UnknownHostException ex) {
           // Logger.getLogger(RMIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "localhost";

    }
    
    public int getPort() {
        Random ran = new Random();
        int k = ran.nextInt(8975) + 1024;
        return k;
    }
    
    public void getID(){
        String host = "localhost";
        try {
            int port = 9876;
            Socket sket = new Socket(host, port);
            oos = new ObjectOutputStream(sket.getOutputStream());
            ois = new ObjectInputStream(sket.getInputStream());
         
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    class SendFileListenner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            File f = mf.getPatchFile();
            if (f == null) return;
            clientContr.sendFile(f);
            mf.ShowMassages("Đã gửi đến: D:\\" + f.getName());
            //SendFile sf = new SendFile(null, patch)
        }
    }

   

    class ConnectListenner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //  lc.stop();
            if (mf.getIP().equals("")) {
                mf.ShowMassages("Chưa nhập ip: ");
                return;
            }
            if (mf.getPort() == -1) {
                mf.ShowMassages("Sai mật khẩu: ");
                return;
            }

            clientContr = new ClientTCP.Controller.ClientController(mf.getIP(), mf.getPort(), mf.getScale());
            if (clientContr.initClient() == false) {
                mf.ShowMassages("Không kết nối được");
                return;
            }
            mf.AciveSend();
        }
    }
}
