/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerRMI.Controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Trung Hoang
 */
public interface RMIInterface extends Remote{
    public String getIP() throws RemoteException;
    public int getPort() throws RemoteException;
    
}
