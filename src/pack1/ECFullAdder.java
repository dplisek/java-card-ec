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

    private ECDifferentAdder differentAdder;
    private ECDoubler doubler;
    private GFOperations operations = new GFOperations();

    public ECFullAdder(GFElement a, GFElement b) {
        differentAdder = new ECDifferentAdder(a);
        doubler = new ECDoubler(computeC(b));
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

    private GFElement computeC(GFElement b) {
        GFElement c = new GFElement(true);
        Util.arrayCopyNonAtomic(b.getBytes(), (short) 0, c.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        for (short i = 0; i < 77; i++) {
            operations.square(c, c);
        }
        return c;
    }

}
