/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerTCP.Controller;

import ClientTCP.Controller.SubFile;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trung Hoang
 */
public class ReceiveEvent extends Thread{
    private Robot robot;
    private ObjectInputStream ois;
    private WriteFile wr;
    private String patch = "File";
    private Dimension d;
    private Toolkit toolkit;
    private Dimension screenSize;
    public ReceiveEvent(ObjectInputStream ois, Dimension d) {
        this.ois = ois;
        this.d = d;
        
       toolkit = Toolkit.getDefaultToolkit();
       screenSize = toolkit.getScreenSize();
        
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(ReceiveEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private Dimension Mousepoit(int x, int y){
        if (d.getWidth() <=0) return new Dimension(x, y);
        double tx = (double)x/d.getWidth();
        double ty = (double)y/d.getHeight();
        Dimension dnew = new Dimension((int)(tx*screenSize.getWidth()),(int)(ty*screenSize.getHeight()));
        return dnew;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                
                Object o = ois.readObject();
                if (o instanceof MouseEvent){
                    MouseEvent e = (MouseEvent) o;
                    int x = e.getX();
                    int y = e.getY();
                   // System.out.println(x + " " + y);
                    Dimension md = Mousepoit(x, y);
                    robot.mouseMove((int)md.getWidth(),(int) md.getHeight());
                   
                    int buttonMask =0 ;
                    int buttons = e.getButton();
                    //System.out.println("Click: " + buttons);
                    if ((buttons == MouseEvent.BUTTON1)) {
                        buttonMask = MouseEvent.BUTTON1_MASK;
                    }
                    else
                    if ((buttons == MouseEvent.BUTTON2)) {
                        buttonMask = MouseEvent.BUTTON2_MASK;
                    }
                    else
                    if ((buttons == MouseEvent.BUTTON3)) {
                        buttonMask = MouseEvent.BUTTON3_MASK;
                    }  
                    System.out.println("Press: " + MouseEvent.MOUSE_PRESSED);
                    System.out.println("Rele: " + MouseEvent.MOUSE_RELEASED);
                    System.out.println("Wheel: " + MouseEvent.MOUSE_WHEEL);
                    System.out.println("ID: " + e.getID());
                    switch(e.getID()) {         
                        case MouseEvent.MOUSE_PRESSED:
                            System.out.println("Press");
                            robot.mousePress(buttonMask);
                            break;
                        case MouseEvent.MOUSE_RELEASED:
                            System.out.println("Relese");
                            robot.mouseRelease(buttonMask);
                            break;
                        case MouseEvent.MOUSE_WHEEL:
                            System.out.println("Wheel");
                            robot.mouseWheel(((MouseWheelEvent) e).getUnitsToScroll()); 
                            break;
                    } 
                }
                else
                    if (o instanceof KeyEvent){
                        try{
                            KeyEvent k = (KeyEvent) o;
                            switch(k.getID()) {
                                case KeyEvent.KEY_PRESSED: robot.keyPress(k.getKeyCode()); break;
                                case KeyEvent.KEY_RELEASED: robot.keyRelease(k.getKeyCode()); break; 
                            }
                        }catch(Exception e){
                            System.out.println("Lỗi phím: " + e);
                        }
                    }
                    else 
                        if (o instanceof String){
                            String p = o.toString();
                            patch = p;
                            System.out.println("File: " + p); 
                             wr = new WriteFile(patch);
                             wr.start();
                            System.out.println("Đã new");       
                        }
                        else
                            if (o instanceof SubFile)
                           {
                               try{
                                System.out.println("Da nhan");
                                SubFile subf = (SubFile) o;
                                wr.Write(subf);
                                System.out.println("Copying...");
                               }catch(Exception e){
                                  // System.out.println("Lỗi này: " + e.toString());
                               }
                            }
                            System.out.println("Success");
                        
            } catch (IOException ex) {
               // System.out.println("Lỗi IO: " + ex.toString());
                //Logger.getLogger(ReceiveEvent.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                //System.out.println("Lỗi: " + ex.toString());
                // Logger.getLogger(RemoteCursor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
