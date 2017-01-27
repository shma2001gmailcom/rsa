package org.misha.impl;

import org.junit.Test;
import org.misha.Rsa;

import static org.junit.Assert.assertEquals;

/**
 * author: misha
 * date: 1/28/17
 * time: 12:08 AM
 */
public class RsaImplTest {

    @Test
    public void decrypt() throws Exception {
        final String original = "Top secret text ";
        final Rsa rsa = new RsaImpl(new FileKeyStorage("../keys/private.key", "../keys/public.key"));
        assertEquals(original, rsa.decrypt(rsa.encrypt(original)));
    }
}