package es.elka.koda.app.file;

import es.elka.koda.app.algorithm.BitsWrapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface EncodedFile {
    void save(List<BitsWrapper> tokens, Map<Byte, BitsWrapper> dictionary) throws IOException;
}
