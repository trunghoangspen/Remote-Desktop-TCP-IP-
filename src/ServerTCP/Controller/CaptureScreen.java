/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerTCP.Controller;



import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Trung Hoang
 */
public class CaptureScreen extends Thread{
   
    private ObjectOutputStream oos;
    private Toolkit toolkit;
    private Dimension screenSize;
    private Rectangle screenRect;
    private Robot robot;
    private ByteArrayOutputStream baos;
    private Dimension d;
    public CaptureScreen(ObjectOutputStream oos, Dimension d) {
        this.oos = oos;
        this.d = d;
       
    }
   
    
    public BufferedImage Resize(BufferedImage bf, double wn, double hn){
        int w = bf.getWidth();
        int h = bf.getHeight();
        double ww = (double)(wn/w);
       
        double hh = (double)(hn/h);
        double test = 0.6;
    //    System.out.println(ww + " " + hh + "=============================" + test);
         AffineTransform tx = new AffineTransform();
              tx.scale(ww, hh);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                bf = op.filter(bf, null);
                return bf;
    }
    
    @Override
    public void run(){
        while(true){
             try 
            {
                this.sleep(30);
            } 
            catch(Exception e) 
            {
                System.exit(1);
            }

            toolkit = Toolkit.getDefaultToolkit();
            screenSize = toolkit.getScreenSize();
            screenRect = new Rectangle(screenSize);
           
            try {
                robot = new Robot();
                BufferedImage temp= robot.createScreenCapture(screenRect);
              //  abc image =  (abc) temp;
                baos = new ByteArrayOutputStream();
                if (d.getWidth() >0 && d.getHeight() > 0)
                temp = Resize(temp, d.getWidth(), d.getHeight());
                
             
               // Image i = temp.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
              
                // System.out.println("wi:"+temp.getWidth());    
                ImageIO.write( temp, "jpg", baos );
                
                baos.flush();
                byte[] imageInByte = baos.toByteArray();
                baos.close();

                oos.writeObject(imageInByte);
               // System.out.println("OK");
            } catch (Exception ex) {
                //Logger.getLogger(CaptureScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
}
