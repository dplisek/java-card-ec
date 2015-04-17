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
public class GFMember {

    private final byte[] bytes = JCSystem.makeTransientByteArray(Applet1.FIELD_WIDTH_BYTES, JCSystem.CLEAR_ON_RESET);
    private final byte[] squaringHelperBytes = JCSystem.makeTransientByteArray((short) (2 * Applet1.FIELD_WIDTH_BYTES), JCSystem.CLEAR_ON_RESET);
    private GFSquaringTable squaringTable = new GFSquaringTable();

    public byte[] getBytes() {
        return bytes;
    }

    public boolean isZero() {
        for (short i = 0; i < Applet1.FIELD_WIDTH_BYTES; i++) {
            if (bytes[i] != (byte) 0x00) {
                return false;
            }
        }
        return true;
    }

    public boolean isOne() {
        for (short i = 0; i < Applet1.FIELD_WIDTH_BYTES - 1; i++) {
            if (bytes[i] != (byte) 0) {
                return false;
            }
        }
        return bytes[Applet1.FIELD_WIDTH_BYTES - 1] == (byte) 0x01;
    }

    public void setZero() {
        for (short i = 0; i < Applet1.FIELD_WIDTH_BYTES; i++) {
            bytes[i] = (byte) 0x00;
        }
    }

    public void setOne() {
        for (short i = 0; i < Applet1.FIELD_WIDTH_BYTES - 1; i++) {
            bytes[i] = (byte) 0x00;
        }
        bytes[Applet1.FIELD_WIDTH_BYTES - 1] = (byte) 0x01;
    }

    public void squared(GFMember out) {
        for (short i = 0; i < Applet1.FIELD_WIDTH_BYTES; i++) {
            byte toSplit = bytes[i];
            byte upper = (byte) ((toSplit >>> 4) & 15);
            byte lower = (byte) (toSplit & 15);
            squaringHelperBytes[(short) (2 * i)] = squaringTable.getSquared(upper);
            squaringHelperBytes[(short) (2 * i + 1)] = squaringTable.getSquared(lower);
        }
        
        for (short i = 0; i < (short) (squaringHelperBytes.length - Applet1.FIELD_WIDTH_BYTES); i++) {
            byte highestXorPattern = (byte) ((squaringHelperBytes[i] >>> 6) & 3);
            byte middleXorPattern = (byte) ((squaringHelperBytes[i] << 2));
            byte lowestXorPattern = (byte) (squaringHelperBytes[i] << 1);
            squaringHelperBytes[(short) (i + 8)] ^= highestXorPattern;
            squaringHelperBytes[(short) (i + 9)] ^= middleXorPattern;
            squaringHelperBytes[(short) (i + 10)] ^= lowestXorPattern;
        }
        
        // WARNING: Ignoring final xor of overlapping byte here, because only the first bit of it is to be reduced,
        // and the first bit is always zero, as the bytes are interleaved with zeros. This is not generally correct, but works here
        // and makes the algorithm shorter.
        
        Util.arrayCopyNonAtomic(squaringHelperBytes, Applet1.FIELD_WIDTH_BYTES, out.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }

    public void times(GFMember other, GFMember out) {
        // TODO
    }

    public void plus(GFMember other, GFMember out) {
        // TODO
    }

    public boolean equals(Object o) {
        if (!(o instanceof GFMember)) {
            return false;
        }
        GFMember other = (GFMember) o;
        for (short i = 0; i < Applet1.FIELD_WIDTH_BYTES; i++) {
            if (bytes[i] != other.bytes[i]) {
                return false;
            }
        }
        return true;
    }

}
