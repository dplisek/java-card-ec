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
public class ECScalarMultiplier {
    
    private static ECScalarMultiplier INSTANCE;
    private final ProjectivePoint temp = new ProjectivePoint();
    
    public static ECScalarMultiplier getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ECScalarMultiplier();
        }
        return INSTANCE;
    }
    
    private ECScalarMultiplier() {
    }
    
    public void scalarMultiply(short scalar, ProjectivePoint in, ProjectivePoint out) {
        out.setInfinity();
        temp.copyOf(in);
        while (scalar > 0) {
            if (scalar % 2 == 1) {
                ECFullAdder.getInstance().add(out, temp, out);
            }
            ECFullAdder.getInstance().getDoubler().doubleElement(temp, temp);
            scalar = (short) (scalar / 2);
        }
    }
    
}
