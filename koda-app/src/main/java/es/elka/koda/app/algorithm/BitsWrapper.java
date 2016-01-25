package es.elka.koda.app.algorithm;

import java.util.BitSet;

public class BitsWrapper {
    private final BitSet bitSet;
    private final byte length;

    public BitsWrapper(BitSet bitSet, byte length) {
        this.bitSet = bitSet;
        this.length = length;
    }

    public BitSet getBitSet() {
        return bitSet;
    }

    public byte getLength() {
        return length;
    }
}
