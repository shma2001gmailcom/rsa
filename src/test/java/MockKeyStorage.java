import org.misha.KeyStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Observable;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.io.IOUtils.toByteArray;
import static org.misha.Rsa.ALGORITHM;

/**
 * author: misha
 * date: 1/15/17
 */
final class MockKeyStorage implements KeyStorage {
    private static final String PATH_TO_YOUR_BASH = "/bin/bash";
    private final String privateKeyPath;
    private final String publicKeyPath;

    MockKeyStorage(final String privateKeyPath, final String publicKeyPath) {
        this.privateKeyPath = privateKeyPath;
        this.publicKeyPath = publicKeyPath;
    }

    public RSAPublicKey getPublic() {
        FileInputStream in = null;
        try {
            in = new FileInputStream(publicKeyPath);
            final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(toByteArray(in));
            return (RSAPublicKey) KeyFactory.getInstance(ALGORITHM).generatePublic(keySpec);
        } catch (Exception e) {
            return null;
        } finally {
            closeQuietly(in);
        }
    }

    public RSAPrivateKey getPrivate() {
        FileInputStream in = null;
        try {
            in = new FileInputStream(privateKeyPath);
            return (RSAPrivateKey) KeyFactory.getInstance(ALGORITHM)
                                             .generatePrivate(new PKCS8EncodedKeySpec(toByteArray(in)));
        } catch (Exception e) {
            return null;
        } finally {
            closeQuietly(in);
        }
    }

    public boolean keyMissed() {
        return !new File(privateKeyPath).exists() || !new File(publicKeyPath).exists();
    }

    public Long getKeyId() {
        return 0L;
    }

    public void update(Observable o, Object arg) {
        try {
            Runtime.getRuntime().exec(new String[]{PATH_TO_YOUR_BASH, "./keygen.sh"});
        } catch (IOException ignored) {
        }
    }
}
