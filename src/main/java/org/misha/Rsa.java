package org.misha;

/**
 * author: misha
 * date: 1/15/17
 * time: 3:23 PM
 */
public interface Rsa {

    String ALGORITHM = "RSA";

    String encrypt(String text);

    String decrypt(String text);
}
