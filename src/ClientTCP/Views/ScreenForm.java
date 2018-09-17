/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientTCP.Views;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Trung Hoang
 */
public class ScreenForm extends javax.swing.JFrame {

    /**
     * Creates new form ScreenForm
     */
    //int t=1;
    private boolean first;
    public ScreenForm() {
        initComponents();
        first = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
    public void KeyActionListener(KeyListener evt){
        labelScreen.addKeyListener(evt);
    }
    public void CursorWheelListenner(MouseWheelListener log){
        labelScreen.addMouseWheelListener(log);
    }
    public void CursorMotionListener(MouseMotionListener log){
        labelScreen.addMouseMotionListener(log);
    }
    public void CursorActionListener(MouseListener log){
        labelScreen.addMouseListener(log);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelScreen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Remote Desktop v2.0");
        setResizable(false);

        labelScreen.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelScreen, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelScreen, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void setSize(int w, int h){
        labelScreen.setSize(w, h);
    }
    
    public void setScreen(byte[] bb){
        try {
            //t++;
            //t = t%2;
            //String imageName = t+ ".jpg";
           // System.out.println("Set");
          //  ImageIO.write(IMGScreen, "JPG", new File(imageName));
            //Graphics2D g2 = IMGScreen.createGraphics();
            
            ImageIcon img = new ImageIcon(bb);
           
            //this.setSize(img.getIconWidth(), img.getIconHeight());
           // ImageIcon img = new ImageIcon(IMGScreen);
           // System.out.println("W:" + img.getIconWidth() + "H: " + img.getIconHeight());
            //this.setSize(img.getIconWidth(), img.getIconHeightget));
            this.resize(img.getIconWidth() + 15, img.getIconHeight() + 35);
            labelScreen.resize(img.getIconWidth(), img.getIconHeight());
           // labelScreen.setSize(img.getIconWidth(), img.getIconHeight());
            labelScreen.setIcon(img);
         //  labelScreen.setIcon(img);
            
            
        } catch (Exception ex) {
            Logger.getLogger(ScreenForm.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Loi Chup Anh");
        }
            //System.out.println("Da luu vao file " + outFileName + ".");
            //sc.setScreen(outFileName);

    }
    public Image toImage(BufferedImage bI){
        return Toolkit.getDefaultToolkit().createImage(bI.getSource());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScreenForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScreenForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScreenForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScreenForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScreenForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton labelScreen;
    // End of variables declaration//GEN-END:variables
}