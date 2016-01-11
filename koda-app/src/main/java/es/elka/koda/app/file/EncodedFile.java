package es.elka.koda.app.file;

import java.io.IOException;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

public interface EncodedFile {
    void save(List<BitSet> tokens, Map<Byte, BitSet> dictionary) throws IOException;
}
