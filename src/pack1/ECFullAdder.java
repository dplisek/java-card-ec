/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack1;

import javacard.framework.Util;

/**
 *
 * @author Dominik
 */
public class ECFullAdder {
    
    private static ECFullAdder INSTANCE;

    public static ECFullAdder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ECFullAdder();
        }
        return INSTANCE;
    }
    
    private final ECDifferentAdder differentAdder;
    private final ECDoubler doubler;

    private ECFullAdder() {
        differentAdder = new ECDifferentAdder(createParamA());
        doubler = new ECDoubler(computeParamC());
    }

    public void add(ProjectivePoint p0, ProjectivePoint p1, ProjectivePoint p2) {
        if (p0.getZ().isZero()) {
            Util.arrayCopyNonAtomic(p1.getX().getBytes(), (short) 0, p2.getX().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
            Util.arrayCopyNonAtomic(p1.getY().getBytes(), (short) 0, p2.getY().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
            Util.arrayCopyNonAtomic(p1.getZ().getBytes(), (short) 0, p2.getZ().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
            return;
        }
        if (p1.getZ().isZero()) {
            Util.arrayCopyNonAtomic(p0.getX().getBytes(), (short) 0, p2.getX().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
            Util.arrayCopyNonAtomic(p0.getY().getBytes(), (short) 0, p2.getY().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
            Util.arrayCopyNonAtomic(p0.getZ().getBytes(), (short) 0, p2.getZ().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
            return;
        }
        differentAdder.addDifferent(p0, p1, p2);
        if (p2.isZero()) {
            doubler.doubleElement(p1, p2);
        }
    }

    public ECDoubler getDoubler() {
        return doubler;
    }
    
    private GFElement createParamA() {
        GFElement a = new GFElement(true);
        a.getBytes()[0] = (byte) 0x4A;
        a.getBytes()[1] = (byte) 0x2E;
        a.getBytes()[2] = (byte) 0x38;
        a.getBytes()[3] = (byte) 0xA8;
        a.getBytes()[4] = (byte) 0xF6;
        a.getBytes()[5] = (byte) 0x6D;
        a.getBytes()[6] = (byte) 0x7F;
        a.getBytes()[7] = (byte) 0x4C;
        a.getBytes()[8] = (byte) 0x38;
        a.getBytes()[9] = (byte) 0x5F;
        return a;
    }
    
    private GFElement createParamB() {
        GFElement b = new GFElement(true);
        b.getBytes()[0] = (byte) 0x2C;
        b.getBytes()[1] = (byte) 0x0B;
        b.getBytes()[2] = (byte) 0xB3;
        b.getBytes()[3] = (byte) 0x1C;
        b.getBytes()[4] = (byte) 0x6B;
        b.getBytes()[5] = (byte) 0xEC;
        b.getBytes()[6] = (byte) 0xC0;
        b.getBytes()[7] = (byte) 0x3D;
        b.getBytes()[8] = (byte) 0x68;
        b.getBytes()[9] = (byte) 0xA7;
        return b;
    }
    
    private GFElement computeParamC() {
        GFElement paramC = new GFElement(true);
        Util.arrayCopyNonAtomic(createParamB().getBytes(), (short) 0, paramC.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        for (short i = 0; i < 77; i++) {
            GFOperations.getInstance().square(paramC, paramC);
        }
        return paramC;
    }
   
}
