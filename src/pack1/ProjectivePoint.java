/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack1;

import javacard.framework.APDU;

/**
 *
 * @author Dominik
 */
public class ProjectivePoint {

    private final GFElement x;
    private final GFElement y;
    private final GFElement z;

    public ProjectivePoint() {
        this(false);
    }
    
    public ProjectivePoint(boolean constant) {
        x = new GFElement(constant);
        y = new GFElement(constant);
        z = new GFElement(constant);
    }
    
    public GFElement getX() {
        return x;
    }

    public GFElement getY() {
        return y;
    }

    public GFElement getZ() {
        return z;
    }

    public boolean isZero() {
        if (!x.isZero()) return false;
        if (!y.isZero()) return false;
        if (!z.isZero()) return false;
        return true;
    }
    
    public void setInfinity() {
        x.setOne();
        y.setOne();
        z.setZero();
    }
    
    public void send(APDU apdu) {
        apdu.sendBytesLong(x.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        apdu.sendBytesLong(y.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        apdu.sendBytesLong(z.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);        
    }
    
}
