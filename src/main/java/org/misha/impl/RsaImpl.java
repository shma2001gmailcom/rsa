package org.misha.impl;

import org.misha.KeyStorage;
import org.misha.Rsa;

import javax.crypto.Cipher;

/**
 * author: misha
 * date: 16.01.2017.
 */
public final class RsaImpl implements Rsa {
    private final KeyStorage keyStorage;

    public RsaImpl(final KeyStorage keyStorage) {
        this.keyStorage = keyStorage;
    }

    public byte[] encrypt(final String text) throws Exception {
        final Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.PUBLIC_KEY, keyStorage.getPublic());
        return cipher.doFinal(text.getBytes());

    }

    public String decrypt(final byte[] encrypted) throws Exception {
        final Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.PRIVATE_KEY, keyStorage.getPrivate());
        return new String(cipher.doFinal(encrypted));
    }
}