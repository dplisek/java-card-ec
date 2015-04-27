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
public class ECDoubler {
    
    private final GFElement paramC;
    
    private final GFElement t1 = new GFElement();
    private final GFElement t2 = new GFElement();
    private final GFElement t3 = new GFElement();
    private final GFElement t4 = new GFElement();
    
    public ECDoubler(GFElement paramC) {
        this.paramC = paramC;
    }

    public void doubleElement(ProjectivePoint p1, ProjectivePoint p2) {
        step1(p1);
        step2(p1);
        step3(p1);
        step4();
        
        // step 5
        if (t1.isZero() || t3.isZero()) {
            p2.getX().setOne();
            p2.getY().setOne();
            p2.getZ().setZero();
            return;
        }
                
        step6();
        step7();
        step8();
        step9();
        step10();
        step11();
        step12();
        step13();
        step14();
        step15();
        step16();
        step17();
        step18();
        step19();
        step20();
        
        Util.arrayCopyNonAtomic(t1.getBytes(), (short) 0, p2.getX().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(t2.getBytes(), (short) 0, p2.getY().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(t3.getBytes(), (short) 0, p2.getZ().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
    private void step1(ProjectivePoint p1) {
         Util.arrayCopyNonAtomic(p1.getX().getBytes(), (short) 0, t1.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
    private void step2(ProjectivePoint p1) {
         Util.arrayCopyNonAtomic(p1.getY().getBytes(), (short) 0, t2.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
    private void step3(ProjectivePoint p1) {
         Util.arrayCopyNonAtomic(p1.getZ().getBytes(), (short) 0, t3.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
    private void step4() {
         Util.arrayCopyNonAtomic(paramC.getBytes(), (short) 0, t4.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
    private void step6() {
        GFOperations.getInstance().multiply(t2, t3, t2);
    }
    
    private void step7() {
        GFOperations.getInstance().square(t3, t3);
    }
    
    private void step8() {
        GFOperations.getInstance().multiply(t3, t4, t4);
    }
    
    private void step9() {
        GFOperations.getInstance().multiply(t1, t3, t3);
    }
    
    private void step10() {
        GFOperations.getInstance().add(t2, t3, t2);
    }
    
    private void step11() {
        GFOperations.getInstance().add(t1, t4, t4);
    }
    
    private void step12() {
        GFOperations.getInstance().square(t4, t4);
    }
    
    private void step13() {
        GFOperations.getInstance().square(t4, t4);
    }
    
    private void step14() {
        GFOperations.getInstance().square(t1, t1);
    }
    
    private void step15() {
        GFOperations.getInstance().add(t1, t2, t2);
    }
    
    private void step16() {
        GFOperations.getInstance().multiply(t2, t4, t2);
    }
    
    private void step17() {
        GFOperations.getInstance().square(t1, t1);
    }
    
    private void step18() {
        GFOperations.getInstance().multiply(t1, t3, t1);
    }
    
    private void step19() {
        GFOperations.getInstance().add(t1, t2, t2);
    }
    
    private void step20() {
        Util.arrayCopyNonAtomic(t4.getBytes(), (short) 0, t1.getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
}
