package es.elka.koda.app.coder;

import java.util.BitSet;
import java.util.Map;

public interface HuffmanAlgorithmServer {
    void initialize();

    void addAndModify(byte token);

    Map<Byte, BitSet> getDictionary();
}
