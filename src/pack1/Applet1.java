/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pack1;

import javacard.framework.*;

/**
 *
 * @author Dominik
 */
public class Applet1 extends Applet {

    public static final short FIELD_WIDTH_BYTES = 10;
        
    private ProjectivePoint p = new ProjectivePoint();
    private ProjectivePoint q = new ProjectivePoint();
    private ProjectivePoint r = new ProjectivePoint();
    
    private ECAdder adder = new ECAdder(createParamA());
    
    /**
     * Installs this applet.
     * 
     * @param bArray
     *            the array containing installation parameters
     * @param bOffset
     *            the starting offset in bArray
     * @param bLength
     *            the length in bytes of the parameter data in bArray
     */
    public static void install(byte[] bArray, short bOffset, byte bLength) {
        new Applet1();
    }

    /**
     * Only this class's install method should create the applet object.
     */
    protected Applet1() {
        register();
    }

    /**
     * Processes an incoming APDU.
     * 
     * @see APDU
     * @param apdu
     *            the incoming APDU
     */
    public void process(APDU apdu) {
        if (selectingApplet()) ISOException.throwIt(ISO7816.SW_NO_ERROR);
        byte[] buf = checkAPDUAndGetData(apdu);
        
        extractP(buf);
        extractQ(buf);
        adder.add(p, q, r);
        
        short lenExp = apdu.setOutgoing();
        if (lenExp < 3 * FIELD_WIDTH_BYTES) {
            ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
        }
        apdu.setOutgoingLength((short) (3 * FIELD_WIDTH_BYTES));
        apdu.sendBytesLong(r.getX().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        apdu.sendBytesLong(r.getY().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        apdu.sendBytesLong(r.getZ().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
    }

    private byte[] checkAPDUAndGetData(APDU apdu) throws APDUException {
        byte[] buf = apdu.getBuffer();
        byte ins = buf[ISO7816.OFFSET_INS];
        if (ins != 0x00) {
            ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
        }
        short len = apdu.setIncomingAndReceive();
        if (len != 6 * FIELD_WIDTH_BYTES) {
            ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
        }
        return buf;
    }

    private void extractP(byte[] buf) {
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 0 * FIELD_WIDTH_BYTES), p.getX().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 1 * FIELD_WIDTH_BYTES), p.getY().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 2 * FIELD_WIDTH_BYTES), p.getZ().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
    }
    
    private void extractQ(byte[] buf) {
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 3 * FIELD_WIDTH_BYTES), q.getX().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 4 * FIELD_WIDTH_BYTES), q.getY().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 5 * FIELD_WIDTH_BYTES), q.getZ().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
    }

    private GFMember createParamA() {
        GFMember a = new GFMember(true);
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

}
