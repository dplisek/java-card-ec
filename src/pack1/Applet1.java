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
        byte ins = apdu.getBuffer()[ISO7816.OFFSET_INS];
        switch (ins) {
            case 0x00:
                AddInstructionHandler.getInstance().handle(apdu);
                break;
            case 0x01:
                GenerateECDHKeyInstructionHandler.getInstance().handle(apdu);
                break;
            default:
                ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
        }    
    }
    
}
