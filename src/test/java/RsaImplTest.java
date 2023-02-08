import org.junit.Before;
import org.junit.Test;
import org.misha.KeyStorage;
import org.misha.Rsa;
import org.misha.impl.RsaImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * author: misha
 * date: 16.01.2017.
 */
public class RsaImplTest {
    private static final String PRIVATE_KEY_PATH = "./resources/keys/private_key.der";
    private static final String PUBLIC_KEY_PATH = "./resources/keys/public_key.der";
    private static final String TOP_SECRET_TEXT = "Top secret text.";
    private String decrypted;

    @Before
    public void init() throws Exception {
        final KeyStorage keyStorage = new MockKeyStorage(PRIVATE_KEY_PATH, PUBLIC_KEY_PATH);
        if (keyStorage.keyMissed()) {
            fail("please, run keygen.sh first");
        }
        final Rsa rsa = new RsaImpl(keyStorage);
        decrypted = rsa.decrypt(rsa.encrypt(TOP_SECRET_TEXT));
    }

    @Test
    public void encrypt() throws Exception {
        assertEquals(decrypted, TOP_SECRET_TEXT);
    }
}