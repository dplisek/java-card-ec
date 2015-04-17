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
    public static final GFMember PARAM_A = createParamA();
    
    private ProjectivePoint p = new ProjectivePoint();
    private ProjectivePoint q = new ProjectivePoint();
    
    private static GFMember createParamA() {
        GFMember a = new GFMember();
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
        
        ProjectivePoint p = extractP(buf);
        ProjectivePoint q = extractQ(buf);
        ProjectivePoint result = ECAdder.add(p, q);
        
        short lenExp = apdu.setOutgoing();
        if (lenExp < 3 * FIELD_WIDTH_BYTES) {
            ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
        }
        apdu.setOutgoingLength((short) (3 * FIELD_WIDTH_BYTES));
        apdu.sendBytesLong(result.getX().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        apdu.sendBytesLong(result.getY().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        apdu.sendBytesLong(result.getZ().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
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

    private ProjectivePoint extractP(byte[] buf) {
        ProjectivePoint pp = new ProjectivePoint();
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 0 * FIELD_WIDTH_BYTES), pp.getX().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 1 * FIELD_WIDTH_BYTES), pp.getY().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 2 * FIELD_WIDTH_BYTES), pp.getZ().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        return pp;
    }
    
    private ProjectivePoint extractQ(byte[] buf) {
        ProjectivePoint pp = new ProjectivePoint();
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 3 * FIELD_WIDTH_BYTES), pp.getX().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 4 * FIELD_WIDTH_BYTES), pp.getY().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        Util.arrayCopyNonAtomic(buf, (short) (ISO7816.OFFSET_CDATA + 5 * FIELD_WIDTH_BYTES), pp.getZ().getBytes(), (short) 0, FIELD_WIDTH_BYTES);
        return pp;
    }

}
