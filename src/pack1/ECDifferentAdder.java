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
public class ECDifferentAdder {

    private GFMember a;
    
    private GFMember t1 = new GFMember();
    private GFMember t2 = new GFMember();
    private GFMember t3 = new GFMember();
    private GFMember t4 = new GFMember();
    private GFMember t5 = new GFMember();
    private GFMember t6 = new GFMember();
    private GFMember t7 = new GFMember();
    private GFMember t8 = new GFMember();
    private GFMember t9 = new GFMember();

    public ECDifferentAdder(GFMember a) {
        this.a = a;
    }
    
    public void addDifferent(ProjectivePoint p0, ProjectivePoint p1, ProjectivePoint p2) {
        step1(p0);
        step2(p0);
        step3(p0);
        step4(p1);
        step5(p1);
        step6();
        step7(p1);
        step8();
        step9();
        step10();
        step11();
        step12();
        step13();
        
        // step 14
        if (t1.isZero()) {
            if (t2.isZero()) {
                p2.getX().setZero();
                p2.getY().setZero();
            } else {
                p2.getX().setOne();
                p2.getY().setOne();
            }
            p2.getZ().setZero();
            return;
        }
        
        step15();
        step16();
        step17();
        step18();
        step19();
        step20();
        step21(p1);
        step22();
        step23();
        step24();
        step25();
        step26();
        step27();
        step28();
        step29();
        
        Util.arrayCopyNonAtomic(t1.getBytes(), (short) 0, p2.getX().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(t2.getBytes(), (short) 0, p2.getY().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(t3.getBytes(), (short) 0, p2.getZ().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }


    private void step1(ProjectivePoint p0) throws ArrayIndexOutOfBoundsException, NullPointerException {
        Util.arrayCopyNonAtomic(p0.getX().getBytes(), (short) 0, t1.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
    private void step2(ProjectivePoint p0) throws ArrayIndexOutOfBoundsException, NullPointerException {
        Util.arrayCopyNonAtomic(p0.getY().getBytes(), (short) 0, t2.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
    private void step3(ProjectivePoint p0) throws ArrayIndexOutOfBoundsException, NullPointerException {
        Util.arrayCopyNonAtomic(p0.getZ().getBytes(), (short) 0, t3.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
    private void step4(ProjectivePoint p1) throws NullPointerException, ArrayIndexOutOfBoundsException {
        Util.arrayCopyNonAtomic(p1.getX().getBytes(), (short) 0, t4.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
    private void step5(ProjectivePoint p1) throws ArrayIndexOutOfBoundsException, NullPointerException {
        Util.arrayCopyNonAtomic(p1.getY().getBytes(), (short) 0, t5.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
    private void step6() throws NullPointerException, ArrayIndexOutOfBoundsException {
        if (!a.isZero()) {
            Util.arrayCopyNonAtomic(a.getBytes(), (short) 0, t9.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        }
    }

    private void step7(ProjectivePoint p1) throws ArrayIndexOutOfBoundsException, NullPointerException {
        if (!p1.getZ().isOne()) {
            Util.arrayCopyNonAtomic(p1.getZ().getBytes(), (short) 0, t6.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
            t6.squared(t7);
            t1.times(t7, t1);
            t6.times(t7, t7);
            t2.times(t7, t2);
        }
    }

    private void step8() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t3.squared(t7);
    }
    
    private void step9() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t4.times(t7, t8);
    }
    
    private void step10() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t1.plus(t8, t1);
    }
    
    private void step11() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t3.times(t7, t7);
    }
    
    private void step12() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t5.times(t7, t8);
    }
    
    private void step13() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t2.plus(t8, t2);
    }
    
    private void step15() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t2.times(t4, t4);
    }
    
    private void step16() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t1.times(t3, t3);
    }
    
    private void step17() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t3.times(t5, t5);
    }
    
    private void step18() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t4.plus(t5, t4);
    }
    
    private void step19() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t3.squared(t5);
    }
    
    private void step20() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t4.times(t5, t7);
    }
    
    private void step21(ProjectivePoint p1) throws NullPointerException, ArrayIndexOutOfBoundsException {
        if (!p1.getZ().isOne()) {
            t3.times(t6, t3);
        }
    }
    
    private void step22() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t2.plus(t3, t4);
    }
    
    private void step23() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t2.times(t4, t2);
    }
    
    private void step24() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t1.squared(t5);
    }
    
    private void step25() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t1.times(t5, t1);
    }
    
    private void step26() throws NullPointerException, ArrayIndexOutOfBoundsException {
        if (!a.isZero()) {
            t3.squared(t8);
            t8.times(t9, t9);
            t1.plus(t9, t1);
        }        
    }
    
    private void step27() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t1.plus(t2, t1);
    }
    
    private void step28() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t1.times(t4, t4);
    }
    
    private void step29() throws NullPointerException, ArrayIndexOutOfBoundsException {
        t4.plus(t7, t2);
    }
    
}
