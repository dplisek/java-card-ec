/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack1;

import javacard.framework.APDU;
import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.Util;

/**
 *
 * @author Dominik
 */
public class GenerateECDHKeyInstructionHandler {
    
    private static GenerateECDHKeyInstructionHandler INSTANCE;
    
    public static GenerateECDHKeyInstructionHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GenerateECDHKeyInstructionHandler();
        }
        return INSTANCE;
    }
    
    private final ProjectivePoint p = createParamP();
    private final ProjectivePoint a = new ProjectivePoint();
    private final ProjectivePoint b = new ProjectivePoint();
    private final ProjectivePoint q = new ProjectivePoint();

    private GenerateECDHKeyInstructionHandler() {
    }
    
    public void handle(APDU apdu) {
        short len = apdu.setIncomingAndReceive();
        if (len < getIncomingLength()) {
            ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
        }
        
        byte[] ka = RandomNumberGenerator.getInstance().generateRandomBytes();
        ECScalarMultiplier.getInstance().scalarMultiply(ka, p, a);
        
        byte[] buf = apdu.getBuffer();
        extractB(buf);
        
        ECScalarMultiplier.getInstance().scalarMultiply(ka, b, q);
        
        short lenExp = apdu.setOutgoing();
        if (lenExp < getOutgoingLength()) {
            ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
        }
        apdu.setOutgoingLength(getOutgoingLength());
        a.send(apdu);
        q.send(apdu);
        apdu.sendBytesLong(ka, (short) 0, (short) Applet1.KEY_LENGTH);
    }

    private void extractB(byte[] buf) {
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 0 * Applet1.FIELD_WIDTH_BYTES), b.getX().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 1 * Applet1.FIELD_WIDTH_BYTES), b.getY().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 2 * Applet1.FIELD_WIDTH_BYTES), b.getZ().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }

    private ProjectivePoint createParamP() {
        ProjectivePoint p = new ProjectivePoint(true);
        
        p.getX().getBytes()[0] = (byte) 0x30;
        p.getX().getBytes()[1] = (byte) 0xCB;
        p.getX().getBytes()[2] = (byte) 0x12;
        p.getX().getBytes()[3] = (byte) 0x7B;
        p.getX().getBytes()[4] = (byte) 0x63;
        p.getX().getBytes()[5] = (byte) 0xE4;
        p.getX().getBytes()[6] = (byte) 0x27;
        p.getX().getBytes()[7] = (byte) 0x92;
        p.getX().getBytes()[8] = (byte) 0xF1;
        p.getX().getBytes()[9] = (byte) 0x0F;
        
        p.getY().getBytes()[0] = (byte) 0x54;
        p.getY().getBytes()[1] = (byte) 0x7B;
        p.getY().getBytes()[2] = (byte) 0x2C;
        p.getY().getBytes()[3] = (byte) 0x88;
        p.getY().getBytes()[4] = (byte) 0x26;
        p.getY().getBytes()[5] = (byte) 0x6B;
        p.getY().getBytes()[6] = (byte) 0xB0;
        p.getY().getBytes()[7] = (byte) 0x4F;
        p.getY().getBytes()[8] = (byte) 0x71;
        p.getY().getBytes()[9] = (byte) 0x3B;
        
        p.getZ().setOne();
        
        return p;
    }

    private short getIncomingLength() {
        return 3 * Applet1.FIELD_WIDTH_BYTES;
    }
    
    private short getOutgoingLength() {
        return 6 * Applet1.FIELD_WIDTH_BYTES + Applet1.KEY_LENGTH;
    }

}
