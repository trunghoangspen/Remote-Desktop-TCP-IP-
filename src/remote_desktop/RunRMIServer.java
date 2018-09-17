/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package remote_desktop;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trung Hoang
 */
public class RunRMIServer {
    public static void main(String[] args) {
        // TODO code application logic here
        //capture c = new capture();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            //java.util.logging.Logger.getLogger(ScreenForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            new ServerRMI.Controller.RMIController();
        } catch (Exception ex) {
            Logger.getLogger(Remote_Desktop.class.getName()).log(Level.SEVERE, null, ex);
        }
     // new ServerTCP.Controller.ServerController();
    }
}
