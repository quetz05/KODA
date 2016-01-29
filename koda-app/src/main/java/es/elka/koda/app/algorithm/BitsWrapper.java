package es.elka.koda.app.algorithm;

import java.util.BitSet;

public class BitsWrapper {
    private final BitSet bitSet;
    /**
     * zapisz jako signed
     */
    private final int length;

    public BitsWrapper(BitSet bitSet, int length) {
        this.bitSet = bitSet;
        this.length = length;
    }

    public BitSet getBitSet() {
        return bitSet;
    }

    public int getLength() {
        return length;
    }
}
