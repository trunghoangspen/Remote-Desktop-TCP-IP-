/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerTCP.Controller;

import ClientTCP.Controller.SubFile;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trung Hoang
 */
public class WriteFile extends Thread{
    
    private String FileName;
 private OutputStream fileStream;
    public WriteFile(String FileName) {
   
        this.FileName = FileName;
        File ffo = new File("D:\\" + FileName);
        try {
            fileStream = new FileOutputStream(ffo);
        } catch (FileNotFoundException ex) {
          //  Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Write(SubFile sf){
        if (sf.getN() == -1){
            try {
             //   System.out.println("Ghi thành công");
                fileStream.flush();
                fileStream.close();
                this.stop();
            } catch (IOException ex) {
              //  Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
          //  System.out.println("N = " + sf.getN());
            fileStream.write(sf.getB(), 0, sf.getN());
        } catch (IOException ex) {
            //Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Lỗi ghi quá lớn");
        }    
    }
    
   
  
    
}
