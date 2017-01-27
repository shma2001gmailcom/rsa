package org.misha;

import org.misha.impl.FileKeyStorage;
import org.misha.impl.RsaImpl;

import java.security.NoSuchAlgorithmException;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * author: misha
 * date: 1/15/17
 * time: 2:55 PM
 */
public class Launcher {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        final KeyStorage keyStorage = new FileKeyStorage("../keys/private.key", "../keys/public.key");
//        if (keyStorage.keyMissed()) {
//            System.out.println("keys aren't present. will be generated new pair.");
//            keyStorage.makeKeys();
//        }
        final String original = "Top secret text ";
        final Rsa rsa = new RsaImpl(keyStorage);
        final String encrypted = rsa.encrypt(original);
        System.out.println("original: " + original);
        System.out.println("encrypted: " + (encrypted != null ? encrypted : EMPTY));
        System.out.println("decrypted: " + rsa.decrypt(encrypted));
    }
}
