/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientTCP.Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sun.misc.IOUtils;

/**
 *
 * @author Trung Hoang
 */
public class SendFile extends Thread {

    private ObjectOutputStream oos;
    private String filepatch;

    public SendFile(ObjectOutputStream oos, String filepatch) {
        this.oos = oos;
        this.filepatch = filepatch;
    }

    @Override
    public void run() {
        BufferedOutputStream bos = null;
        try {
           // System.out.println("Gửi file: " + filepatch);
            File f = new File(filepatch);
          //  System.out.println("Do dai truoc khi doc: " + f.length());
            FileInputStream fi = new FileInputStream(f);
          //File ffo = new File("C:\\okkkkkkkkkkkkkkkk.jpg");
           // FileOutputStream fo = new FileOutputStream(ffo);
            BufferedInputStream bis = new BufferedInputStream(fi);
           // bos = new BufferedOutputStream(fo);
          //  System.out.println("Copying...");
            byte[] tt;
            try{
               tt = new byte[(int)f.length()];
            }catch(OutOfMemoryError e){
              //  System.out.println("Lỗi ép kiểu");
               // JOptionPane.showMessageDialog(null, e, filepatch, MIN_PRIORITY, null);
                JOptionPane.showMessageDialog(null, "Kích thước file vượt quá dung lượng cho phép!", "Kích thước file quá lớn!", 0, null);
                return;
            }
            byte[] tmp = new byte[1024];
                
            //oos.writeObject(f);
            int k  =0;
            while (true) {
                try {
                    k = bis.read(tt);
                    
                    SubFile sf = new SubFile(tt, k);
                    oos.writeObject(sf);
                   if (k == -1){
                        break;
                    }
                } catch (Exception e) {
                   // System.out.println("Lỗi gửi...");
                    break;
                }
            }
            
           // System.out.println("Success");
            
        } catch (Exception ex) {
           
        } finally {
          
        }
    }
}
