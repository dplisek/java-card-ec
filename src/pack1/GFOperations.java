/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack1;

import javacard.framework.JCSystem;
import javacard.framework.Util;

/**
 *
 * @author Dominik
 */
public class GFOperations {

    private final byte[] multiplicationHelperBytes = JCSystem.makeTransientByteArray((short) (Applet1.FIELD_WIDTH_BYTES + 1), JCSystem.CLEAR_ON_RESET);
    private final byte[] reductionTempBytes = JCSystem.makeTransientByteArray((short) (2 * Applet1.FIELD_WIDTH_BYTES), JCSystem.CLEAR_ON_RESET);
    private GFSquaringTable squaringTable = new GFSquaringTable();

    public void square(GFMember in, GFMember out) {
        for (short i = 0; i < Applet1.FIELD_WIDTH_BYTES; i++) {
            byte toSplit = in.getBytes()[i];
            byte upper = (byte) ((toSplit >>> 4) & 15);
            byte lower = (byte) (toSplit & 15);
            reductionTempBytes[(short) (2 * i)] = squaringTable.getSquared(upper);
            reductionTempBytes[(short) (2 * i + 1)] = squaringTable.getSquared(lower);
        }
        reduce();
        Util.arrayCopyNonAtomic(reductionTempBytes, Applet1.FIELD_WIDTH_BYTES, out.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
    public void multiply(GFMember in, GFMember other, GFMember out) {
        Util.arrayFillNonAtomic(reductionTempBytes, (short) 0, (short) (2 * Applet1.FIELD_WIDTH_BYTES), (byte) 0);
        multiplicationHelperBytes[0] = (byte) 0;
        Util.arrayCopyNonAtomic(in.getBytes(), (short) 0, multiplicationHelperBytes, (short) 1, Applet1.FIELD_WIDTH_BYTES);
        byte bitMask = 1;
        for (short j = 7; j >= 0; j--) {
            for (short i = Applet1.FIELD_WIDTH_BYTES - 1; i >= 0; i--) {
                if ((other.getBytes()[i] & bitMask) != 0) {
                    for (short k = 0; k < Applet1.FIELD_WIDTH_BYTES + 1; k++) {
                        reductionTempBytes[(short) (i + k)] ^= multiplicationHelperBytes[k];
                    }
                }
            }
            shiftMultiplicationHelperBitsLeft();
            bitMask = (byte) (bitMask << 1);
        }
        reduce();
        Util.arrayCopyNonAtomic(reductionTempBytes, Applet1.FIELD_WIDTH_BYTES, out.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
    private void shiftMultiplicationHelperBitsLeft() {
        for (short i = 0; i < Applet1.FIELD_WIDTH_BYTES; i++) {
            multiplicationHelperBytes[i] = (byte) (multiplicationHelperBytes[i] << 1); 
            byte highestBitOfLowerByte = (byte) (multiplicationHelperBytes[(short) (i + 1)] & 0x80);
            highestBitOfLowerByte = (byte) ((highestBitOfLowerByte >>> 7) & 1);
            multiplicationHelperBytes[i] |= highestBitOfLowerByte;
        }
        multiplicationHelperBytes[Applet1.FIELD_WIDTH_BYTES] = (byte) (multiplicationHelperBytes[Applet1.FIELD_WIDTH_BYTES] << 1);
    }
    
    private void reduce() {
        for (short i = 0; i < Applet1.FIELD_WIDTH_BYTES; i++) {
            byte highestXorPattern = (byte) ((reductionTempBytes[i] >>> 6) & 3);
            byte middleXorPattern = (byte) ((reductionTempBytes[i] << 2));
            middleXorPattern = (byte) (middleXorPattern ^ ((reductionTempBytes[i] >>> 7) & 1));
            byte lowestXorPattern = (byte) (reductionTempBytes[i] << 1);
            reductionTempBytes[i] = 0;
            reductionTempBytes[(short) (i + 8)] ^= highestXorPattern;
            reductionTempBytes[(short) (i + 9)] ^= middleXorPattern;
            reductionTempBytes[(short) (i + 10)] ^= lowestXorPattern;
        }
        byte highXorByte = (byte) ((reductionTempBytes[Applet1.FIELD_WIDTH_BYTES] >>> 6) & 2);
        byte lowXorByte = (byte) ((reductionTempBytes[Applet1.FIELD_WIDTH_BYTES] >>> 7) & 1);
        reductionTempBytes[Applet1.FIELD_WIDTH_BYTES] &= 0x7F;
        reductionTempBytes[(short) (2 * Applet1.FIELD_WIDTH_BYTES - 2)] ^= highXorByte;
        reductionTempBytes[(short) (2 * Applet1.FIELD_WIDTH_BYTES - 1)] ^= lowXorByte;
    }
    
    public void add(GFMember in, GFMember other, GFMember out) {
        for (short i = 0; i < Applet1.FIELD_WIDTH_BYTES; i++) {
            out.getBytes()[i] = (byte) (in.getBytes()[i] ^ other.getBytes()[i]);
        }
    }
}
