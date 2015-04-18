/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack1;

import javacard.framework.JCSystem;

/**
 *
 * @author Dominik
 */
public class GFSquaringTable {
    
    private byte[] squaredHalfBytes = new byte[16];
    
    public GFSquaringTable() {
        squaredHalfBytes[0] = (byte) 0;
        squaredHalfBytes[1] = (byte) 1;
        squaredHalfBytes[2] = (byte) 4;
        squaredHalfBytes[3] = (byte) 5;
        squaredHalfBytes[4] = (byte) 16;
        squaredHalfBytes[5] = (byte) 17;
        squaredHalfBytes[6] = (byte) 20;
        squaredHalfBytes[7] = (byte) 21;
        squaredHalfBytes[8] = (byte) 64;
        squaredHalfBytes[9] = (byte) 65;
        squaredHalfBytes[10] = (byte) 68;
        squaredHalfBytes[11] = (byte) 69;
        squaredHalfBytes[12] = (byte) 80;
        squaredHalfBytes[13] = (byte) 81;
        squaredHalfBytes[14] = (byte) 84;
        squaredHalfBytes[15] = (byte) 85;
    }
    
    public byte getSquared(byte halfByte) {
        return squaredHalfBytes[halfByte];
    }
}
