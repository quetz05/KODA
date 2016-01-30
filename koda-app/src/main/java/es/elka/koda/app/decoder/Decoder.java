package es.elka.koda.app.decoder;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class Decoder {
    private final byte[] bytes;
    private final Map<BitSet, Byte> dictionary;
    private BitSet bits;
    private int currentIndex = 0;
    private int nextKeyLength;
    private boolean[] bools;

    public Decoder(byte[] bytes) {
        this.bytes = bytes;
        dictionary = new HashMap<>();
    }

    public void decode() {

        boolean isItEnd = false;
        byte b = 0;
        // byte[] bytesssss = {b, -127, -8, 32};
        bits = BitSet.valueOf(bytes);
        int size = bytes.length * 8;
        bools = new boolean[size];
        bits.stream().forEach(x -> bools[x] = true);

        while (!this.isItEnd()) {

            Byte value = readValue();
            this.nextKeyLength = readKeyLength();
            BitSet nextKey = readNextKey();
            dictionary.put(nextKey, value);
        }
    }

    private byte readValue() {
        int endIndex = currentIndex + 8;
        BitSet val = this.bits.get(currentIndex, endIndex);
        boolean[] bbb = Arrays.copyOfRange(this.bools, currentIndex, endIndex);

        BitSet leTeste = new BitSet(8);

        for (int i = 0; i < bbb.length; ++i) {
            if (bbb[i]) leTeste.set(i);
        }

        byte[] aaaa = leTeste.toByteArray();

        byte[] b = val.toByteArray();

        currentIndex = endIndex;

        if (val.length() == 0)
            return 0;
        else

            return b[0];
    }

    private int readKeyLength() {
        byte length = this.readValue();

        int iLen = length;
        return iLen;
    }

    private BitSet readNextKey() {
        int endIndex = currentIndex + this.nextKeyLength;
        boolean[] bbb = Arrays.copyOfRange(this.bools, currentIndex, endIndex);
        BitSet val = this.bits.get(currentIndex, endIndex);
        int czoTo = nextKeyLength - val.length();
        currentIndex = endIndex;
        BitSet leTeste = new BitSet(nextKeyLength);

        for (int i = 0; i < bbb.length; ++i) {
            if (bbb[i]) leTeste.set(i);
        }

        byte[] aaaa = leTeste.toByteArray();
        return val;
    }

    private boolean isItEnd() {

        BitSet val = this.bits.get(currentIndex, currentIndex + 16);
        byte[] bytes = val.toByteArray();

        return (bytes[0] == 0 && (bytes[1] == 0));
    }
}
