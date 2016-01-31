package es.elka.koda.app.algorithm;

import java.util.BitSet;

public class BitsWrapper {
    /**
     * zapisz jako signed
     */
    private final int length;
    private BitSet bitSet;

    public BitsWrapper(BitSet bitSet, int length) {
        this.bitSet = bitSet;
        this.length = length;

    }

    public BitSet getBitSet() {
        return bitSet;
    }

    public void setBitSet(BitSet bitSet) {
        this.bitSet = bitSet;
    }

    public int getLength() {
        return length;
    }
}
