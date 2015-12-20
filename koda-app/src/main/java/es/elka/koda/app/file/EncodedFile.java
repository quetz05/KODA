package es.elka.koda.app.file;

import java.io.IOException;
import java.util.BitSet;
import java.util.List;

public interface EncodedFile {
    void save(List<BitSet> tokens) throws IOException;
}
