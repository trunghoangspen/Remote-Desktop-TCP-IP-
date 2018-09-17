/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientTCP.Controller;

import java.io.Serializable;

/**
 *
 * @author Trung Hoang
 */
public class SubFile implements Serializable{
    private byte[] b;
    int n;

    public SubFile() {
    }

    public SubFile(byte[] b, int n) {
        this.b = b;
        this.n = n;
    }

    public byte[] getB() {
        return b;
    }

    public void setB(byte[] b) {
        this.b = b;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
    
}
