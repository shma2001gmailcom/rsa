package org.misha;

/**
 * author: misha
 * date: 14.12.2016.
 * Made abstract for testing ability
 */
public abstract class Encrypter {

    protected abstract byte[] encrypt(final String input) throws Exception;
}
