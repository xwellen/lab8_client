package Interfaces;

import java.io.IOException;

public interface Decrypting {
    void decrypt(Object o) throws IOException;

    void requireCollection() throws InterruptedException, ClassNotFoundException;
}
