package org.misha;

/**
 * author: misha
 * date: 1/15/17
 * time: 3:23 PM
 */
public interface Rsa {
    String ALGORITHM = "RSA";

    byte[] encrypt(String text) throws Exception;

    String decrypt(final byte[] text) throws Exception;
}
