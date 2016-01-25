package es.elka.koda.app.coder;

import es.elka.koda.app.algorithm.BitsWrapper;

import java.util.Map;

public interface HuffmanAlgorithmServer {
    void initialize();

    void addAndModify(byte token);

    void createDictionary();

    Map<Byte, BitsWrapper> getDictionary();
}
