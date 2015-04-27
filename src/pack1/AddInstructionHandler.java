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
public class AddInstructionHandler {
    
    private static AddInstructionHandler INSTANCE;
    
    public static AddInstructionHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddInstructionHandler();
        }
        return INSTANCE;
    }
    
    private final ProjectivePoint p = new ProjectivePoint();
    private final ProjectivePoint q = new ProjectivePoint();
    private final ProjectivePoint r = new ProjectivePoint();
    
    private AddInstructionHandler() {
    }
    
    public void handle(APDU apdu) {
        short len = apdu.setIncomingAndReceive();
        if (len < getIncomingLength()) {
            ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
        }
        
        byte[] buf = apdu.getBuffer();
        extractP(buf);
        extractQ(buf);
        
        ECFullAdder.getInstance().add(p, q, r);
        
        short lenExp = apdu.setOutgoing();
        if (lenExp < getOutgoingLength()) {
            ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
        }
        apdu.setOutgoingLength(getOutgoingLength());
        r.send(apdu);
    }

    private short getIncomingLength() {
        return 6 * Applet1.FIELD_WIDTH_BYTES;
    }

    private short getOutgoingLength() {
        return 3 * Applet1.FIELD_WIDTH_BYTES;
    }
    
    private void extractP(byte[] buf) {
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 0 * Applet1.FIELD_WIDTH_BYTES), p.getX().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 1 * Applet1.FIELD_WIDTH_BYTES), p.getY().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 2 * Applet1.FIELD_WIDTH_BYTES), p.getZ().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
    
    private void extractQ(byte[] buf) {
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 3 * Applet1.FIELD_WIDTH_BYTES), q.getX().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 4 * Applet1.FIELD_WIDTH_BYTES), q.getY().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 5 * Applet1.FIELD_WIDTH_BYTES), q.getZ().getBytes(), (short) 0, Applet1.FIELD_WIDTH_BYTES);
    }
}
