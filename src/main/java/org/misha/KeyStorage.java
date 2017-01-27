package org.misha;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Observer;

/**
 * author: misha
 * date: 1/15/17
 * time: 2:51 PM
 */
public interface KeyStorage extends Observer {

    PublicKey getPublic() throws Exception;

    PrivateKey getPrivate();

    boolean keyMissed();

    Long getKeyId() throws Exception;
}
