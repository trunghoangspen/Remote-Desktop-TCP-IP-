/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientTCP.Controller;

import ClientTCP.Views.ScreenForm;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trung Hoang
 */
public class SetScreen extends Thread {

    private ScreenForm sf;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    
    public SetScreen(ObjectInputStream ois, ObjectOutputStream oos) {
        sf = new ScreenForm();
        sf.show();
        this.ois = ois;
        this.oos = oos;
       
      //  System.out.println("OK");
    }

    @Override
    public void run() {
        SendEvent se = new SendEvent(oos, sf);
        se.start();
        while (true) {
            Object o;
            try {
                o = ois.readObject();
               // Image i = (Image) o;
                byte[] bb = (byte[]) o;
               
                sf.setScreen(bb);
            } catch (Exception ex) {
               // Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //}
        }
    }
}
