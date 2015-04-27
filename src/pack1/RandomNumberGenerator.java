/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack1;

import javacard.framework.JCSystem;
import javacard.security.CryptoException;
import javacard.security.RandomData;

/**
 *
 * @author Dominik
 */
public class RandomNumberGenerator {
    
    private static RandomNumberGenerator INSTANCE;

    public static RandomNumberGenerator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RandomNumberGenerator();
        }
        return INSTANCE;
    }
    
    private final byte[] buf = JCSystem.makeTransientByteArray(Applet1.KEY_LENGTH, JCSystem.CLEAR_ON_RESET);
    private RandomData randomData;

    private RandomNumberGenerator() {
        try {
            randomData = RandomData.getInstance(RandomData.ALG_SECURE_RANDOM);
        } catch (CryptoException e) {
            if (e.getReason() == CryptoException.NO_SUCH_ALGORITHM) {
                // we are in simulator, use pseudo random
                randomData = RandomData.getInstance(RandomData.ALG_PSEUDO_RANDOM);
            } else {
                throw e;
            }
        }
    }
    
    public byte[] generateRandomBytes() {
        randomData.generateData(buf, (short) 0, Applet1.KEY_LENGTH);
        return buf;
    }
    
}
