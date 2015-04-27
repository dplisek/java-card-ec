/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack1;

/**
 *
 * @author Dominik
 */
public class ECScalarMultiplier {
    
    private static ECScalarMultiplier INSTANCE;
    
    public static ECScalarMultiplier getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ECScalarMultiplier();
        }
        return INSTANCE;
    }
    
    private ECScalarMultiplier() {
    }
    
    public void scalarMultiply(byte[] scalar, ProjectivePoint in, ProjectivePoint out) {
        out.setInfinity();
        for (short i = (short) (Applet1.KEY_LENGTH - 1); i >= 0; i--) {
            byte n = scalar[i];
            for (short j = 0; j < 8; j++) {
                if ((n & 0x01) != 0) {
                    ECFullAdder.getInstance().add(out, in, out);
                }
                ECFullAdder.getInstance().getDoubler().doubleElement(in, in);
                n = (byte) ((n >>> 1) & 0x7F);
            }
        }
    }
    
}
