/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientTCP.Controller;

import ClientTCP.Views.ScreenForm;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trung Hoang
 */
public class SendEvent extends Thread {

    private ObjectOutputStream oos;
    private ScreenForm sf;

    public SendEvent(ObjectOutputStream oos, ScreenForm sf) {
        this.oos = oos;
        this.sf = sf;
        this.sf.CursorMotionListener(new MouseMotion());
        this.sf.CursorActionListener(new MouseAction());
        this.sf.CursorWheelListenner(new MouseWheel());
        this.sf.KeyActionListener(new KeyAction());
    }

    class MouseWheel implements MouseWheelListener {

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            try {
                oos.writeObject(e);

                // throw new UnsupportedOperationException("Not supported yet.");
            } catch (Exception ex) {
            }
        }
    }

    class KeyAction implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // System.out.println("Key Typed: "+e.getKeyCode());
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println(
            //e.ACTION_EVENT_MASK);
            System.out.println("Key Pressed: " + e.getKeyCode());
            try {
                oos.writeObject(e);

                // throw new UnsupportedOperationException("Not supported yet.");
            } catch (Exception ex) {
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            System.out.println("Key Released: " + e.getKeyCode());

            try {
                oos.writeObject(e);

                // throw new UnsupportedOperationException("Not supported yet.");
            } catch (Exception ex) {
            }
        }
    }

    class MouseMotion implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("Drag: " + e.getXOnScreen() + " " + e.getYOnScreen());
            try {
                // oos.writeObject(e);
                // throw new UnsupportedOperationException("Not supported yet.");
            } catch (Exception ex) {
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            System.out.println("Move: " + e.getXOnScreen() + " " + e.getYOnScreen());
            try {
                oos.writeObject(e);

                // throw new UnsupportedOperationException("Not supported yet.");
            } catch (Exception ex) {
            }
            // throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    class MouseAction implements MouseListener {

       

        @Override
        public void mousePressed(MouseEvent e) {
            // System.out.println("Pressed: " + e.getXOnScreen() + " " + e.getYOnScreen()); 
            try {
                oos.writeObject(e);

                // throw new UnsupportedOperationException("Not supported yet.");
            } catch (Exception ex) {
            }
            // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // System.out.println("Released: " + e.getXOnScreen() + " " + e.getYOnScreen());
            try {
                oos.writeObject(e);
                //throw new UnsupportedOperationException("Not supported yet.");
            } catch (Exception ex) {
                Logger.getLogger(SetScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // System.out.println("Entered:: " + e.getXOnScreen() + " " + e.getYOnScreen());
            try {
                // oos.writeObject(e);
                //throw new UnsupportedOperationException("Not supported yet.");
            } catch (Exception ex) {
                Logger.getLogger(SetScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //System.out.println("Exited: " + e.getXOnScreen() + " " + e.getYOnScreen());
            try {
                // oos.writeObject(e);
                //throw new UnsupportedOperationException("Not supported yet.");
            } catch (Exception ex) {
               // Logger.getLogger(SetScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            //  throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
