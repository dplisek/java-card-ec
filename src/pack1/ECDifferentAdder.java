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

    private final GFElement paramA;
    
    private final GFElement t1 = new GFElement();
    private final GFElement t2 = new GFElement();
    private final GFElement t3 = new GFElement();
    private final GFElement t4 = new GFElement();
    private final GFElement t5 = new GFElement();
    private final GFElement t6 = new GFElement();
    private final GFElement t7 = new GFElement();
    private final GFElement t8 = new GFElement();
    private final GFElement t9 = new GFElement();
    
    public ECDifferentAdder(GFElement paramA) {
        this.paramA = paramA;
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
        if (!paramA.isZero()) {
            Util.arrayCopyNonAtomic(paramA.getBytes(), (short) 0, t9.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        }
    }

    private void step7(ProjectivePoint p1) throws ArrayIndexOutOfBoundsException, NullPointerException {
        if (!p1.getZ().isOne()) {
            Util.arrayCopyNonAtomic(p1.getZ().getBytes(), (short) 0, t6.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
            GFOperations.getInstance().square(t6, t7);
            GFOperations.getInstance().multiply(t1, t7, t1);
            GFOperations.getInstance().multiply(t6, t7, t7);
            GFOperations.getInstance().multiply(t2, t7, t2);
        }
    }

    private void step8() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().square(t3, t7);
    }
    
    private void step9() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().multiply(t4, t7, t8);
    }
    
    private void step10() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().add(t1, t8, t1);
    }
    
    private void step11() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().multiply(t3, t7, t7);
    }
    
    private void step12() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().multiply(t5, t7, t8);
    }
    
    private void step13() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().add(t2, t8, t2);
    }
    
    private void step15() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().multiply(t2, t4, t4);
    }
    
    private void step16() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().multiply(t1, t3, t3);
    }
    
    private void step17() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().multiply(t3, t5, t5);
    }
    
    private void step18() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().add(t4, t5, t4);
    }
    
    private void step19() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().square(t3, t5);
    }
    
    private void step20() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().multiply(t4, t5, t7);
    }
    
    private void step21(ProjectivePoint p1) throws NullPointerException, ArrayIndexOutOfBoundsException {
        if (!p1.getZ().isOne()) {
            GFOperations.getInstance().multiply(t3, t6, t3);
        }
    }
    
    private void step22() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().add(t2, t3, t4);
    }
    
    private void step23() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().multiply(t2, t4, t2);
    }
    
    private void step24() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().square(t1, t5);
    }
    
    private void step25() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().multiply(t1, t5, t1);
    }
    
    private void step26() throws NullPointerException, ArrayIndexOutOfBoundsException {
        if (!paramA.isZero()) {
            GFOperations.getInstance().square(t3, t8);
            GFOperations.getInstance().multiply(t8, t9, t9);
            GFOperations.getInstance().add(t1, t9, t1);
        }        
    }
    
    private void step27() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().add(t1, t2, t1);
    }
    
    private void step28() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().multiply(t1, t4, t4);
    }
    
    private void step29() throws NullPointerException, ArrayIndexOutOfBoundsException {
        GFOperations.getInstance().add(t4, t7, t2);
    }
    
}
