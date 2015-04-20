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
public class GFMember {

    private final byte[] bytes;
    
    public GFMember() {
        this(false);
    }
    
    public GFMember(boolean constant) {
        if (constant) {
            bytes = new byte[Applet1.FIELD_WIDTH_BYTES];
        } else {
            bytes = JCSystem.makeTransientByteArray(Applet1.FIELD_WIDTH_BYTES, JCSystem.CLEAR_ON_RESET);
        }
    }
    
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
