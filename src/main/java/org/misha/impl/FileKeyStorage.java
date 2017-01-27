package org.misha.impl;

import org.misha.KeyStorage;

import java.io.*;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.misha.Rsa.ALGORITHM;

/**
 * author: misha
 * date: 1/15/17
 * time: 2:53 PM
 */
class FileKeyStorage extends KeyStorage {
    private final String privateKeyPath;
    private final String publicKeyPath;
    private final Map<String, File> keyFiles;

    FileKeyStorage(final String privateKeyPath, final String publicKeyPath) throws NoSuchAlgorithmException {
        this.privateKeyPath = privateKeyPath;
        this.publicKeyPath = publicKeyPath;
        keyFiles = new HashMap<String, File>() {{
            put(privateKeyPath, new File(privateKeyPath));
            put(publicKeyPath, new File(publicKeyPath));
        }};
    }

    private static void write(final Key key, final File file) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(key);
        } finally {
            closeQuietly(out);
        }
    }

    @Override
    public PublicKey getPublic() {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(publicKeyPath));
            return (PublicKey) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeQuietly(in);
        }
    }

    @Override
    public PrivateKey getPrivate() {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(privateKeyPath));
            return (PrivateKey) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeQuietly(in);
        }
    }

    @Override
    protected void makeKeys() throws NoSuchAlgorithmException {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024);
            writeKeys(keyGen.generateKeyPair());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeKeys(final KeyPair keys) throws IOException {
        writeKeyFile(privateKeyPath, keys);
        writeKeyFile(publicKeyPath, keys);
    }

    private void writeKeyFile(final String name, final KeyPair keys) throws IOException {
        final File file = keyFiles.get(name);
        final File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        file.createNewFile();
        write(name.equals(privateKeyPath) ? keys.getPrivate() : keys.getPublic(), file);
    }

    @Override
    protected boolean keyMissed() {
        return !new File(privateKeyPath).exists() || !new File(publicKeyPath).exists();
    }
}
