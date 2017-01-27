package org.misha;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * author: misha
 * date: 1/15/17
 * time: 2:51 PM
 */
public abstract class KeyStorage {

    public abstract PublicKey getPublic();

    public abstract PrivateKey getPrivate();

    protected abstract boolean keyMissed();

    protected abstract void makeKeys() throws NoSuchAlgorithmException;
}
