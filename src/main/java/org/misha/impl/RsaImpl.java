package org.misha.impl;

import org.apache.commons.codec.binary.Base64;
import org.misha.KeyStorage;
import org.misha.Rsa;

import javax.crypto.Cipher;

class RsaImpl implements Rsa {
    private static final String CHARSET = "UTF-8";
    private final KeyStorage keyStorage;
    private final Base64 base64 = new Base64();

    RsaImpl(final KeyStorage keyStorage) {
        this.keyStorage = keyStorage;
    }

    @Override
    public String encrypt(final String text) {
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keyStorage.getPublic());
            return new String(base64.encode(cipher.doFinal(text.getBytes(CHARSET))));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String decrypt(final String text) {
        String decryptedText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keyStorage.getPrivate());
            decryptedText = new String(cipher.doFinal(base64.decode(text)), CHARSET);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (decryptedText == null) {
            throw new IllegalStateException("decrypted should be non-null");
        }
        return decryptedText;
    }
}