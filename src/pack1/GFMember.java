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

    private final byte[] bytes = JCSystem.makeTransientByteArray(Applet1.FIELD_WIDTH_BYTES, JCSystem.CLEAR_ON_RESET);

    public byte[] getBytes() {
        return bytes;
    }

    public boolean isZero() {
        for (byte b : bytes) {
            if (b != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isOne() {
        for (int i = 0; i < Applet1.FIELD_WIDTH_BYTES - 1; i++) {
            if (bytes[i] != 0) {
                return false;
            }
        }
        return bytes[Applet1.FIELD_WIDTH_BYTES - 1] == 0x01;
    }

    public void squared(GFMember out) {
        // TODO
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
        for (int i = 0; i < Applet1.FIELD_WIDTH_BYTES; i++) {
            if (bytes[i] != other.bytes[i]) {
                return false;
            }
        }
        return true;
    }

    public static GFMember createOne() {
        GFMember one = new GFMember();
        one.bytes[Applet1.FIELD_WIDTH_BYTES - 1] = (byte) 0x01;
        return one;
    }

}
