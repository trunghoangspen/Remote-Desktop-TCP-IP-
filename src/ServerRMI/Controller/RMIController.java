/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerRMI.Controller;

import ServerRMI.Views.RMIViews;
import java.net.*;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trung Hoang
 */
public class RMIController extends UnicastRemoteObject implements RMIInterface {

    private int port = 4444;
    private Registry registry;
    private String rmiserver = "RMIServer";
    private RMIViews views;

    public RMIController() throws RemoteException {

        try {
            registry = LocateRegistry.createRegistry(port);
            registry.bind(rmiserver, this);
        } catch (Exception ex) {
            System.out.println(ex);

        }
        views = new RMIViews();
        views.setIP(getIP());
        views.setport("" + 4444);
        views.show();
    }

    @Override
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

    @Override
    public int getPort() {
        Random ran = new Random();
        int k = ran.nextInt(8975) + 1024;
        return k;
    }
}
