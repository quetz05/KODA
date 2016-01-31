package es.elka.koda.app.decoder;

import java.util.*;

public class Decoder {
    private final byte[] bytes;
    private final Map<CodeWrapper, Byte> dictionary;
    private int currentIndex = 0;
    private int nextKeyLength;
    private Boolean[] bools;
    private int totalValuesToDecode;

    public Decoder(byte[] bytes) {
        this.bytes = bytes;
        dictionary = new HashMap<>();
    }

    public byte[] decode() {

        BitSet bits = BitSet.valueOf(bytes);
        int size = bytes.length * 8;
        this.bools = new Boolean[size];
        Arrays.fill(this.bools, Boolean.FALSE);


        boolean[] bitsToProcess = new boolean[8];
        bits.stream().forEach(x -> this.bools[x] = true);
        Boolean first = true;
        for (int i = 0; i < this.bools.length; ++i) {
            int idx = i % 8;

            if (idx == 0 && !first) {
                this.swapBits(bitsToProcess, i);
            }

            bitsToProcess[idx] = this.bools[i];

            if (first)
                first = false;
        }
        this.swapBits(bitsToProcess, this.bools.length);
        while (!this.isItEnd()) {

            Byte value = readValue();
            this.nextKeyLength = readKeyLength();
            Boolean[] nextKey = readNextKey();
            dictionary.put(new CodeWrapper(nextKey), value);
        }
        this.totalValuesToDecode = this.readKeyNumber();

        return this.decodeMeDatShit();
    }

    private int readKeyNumber() {
        int endIndex = currentIndex + 32;
        Boolean[] bitValues = Arrays.copyOfRange(this.bools, currentIndex, endIndex);

        BitSet bitsetBits = new BitSet(32);

        for (int i = 0; i < bitValues.length; ++i) {
            if (bitValues[i]) bitsetBits.set(31 - i);
        }


        currentIndex = endIndex;

        return (int) bitsetBits.toLongArray()[0];
    }

    private byte[] decodeMeDatShit() {
        ArrayList<Byte> decodedBytes = new ArrayList<>();
        ArrayList<Boolean> values = new ArrayList<>();
        int leftToDecode = this.totalValuesToDecode;
        for (int i = this.currentIndex; i < this.bools.length; ++i) {
            if(leftToDecode == 0)
                break;

            values.add(this.bools[i]);
            Boolean[] op = values.toArray(new Boolean[values.size()]);
            CodeWrapper mm = new CodeWrapper(op);

            Byte decodedByte = dictionary.get(mm);
            if (decodedByte != null) {
                leftToDecode--;
                decodedBytes.add(decodedByte);
                values.clear();
            }
        }

        return this.convertByteObjectArray(decodedBytes);
    }

    private byte readValue() {
        int endIndex = currentIndex + 8;

        Boolean[] bitValues = Arrays.copyOfRange(this.bools, currentIndex, endIndex);

        BitSet bitsetBits = new BitSet(8);

        for (int i = 0; i < bitValues.length; ++i) {
            if (bitValues[i]) bitsetBits.set(7 - i);
        }

        byte[] bytesValue = bitsetBits.toByteArray();

        currentIndex = endIndex;

        if (bitsetBits.length() == 0)
            return 0;
        else
            return bytesValue[0];
    }

    private int readKeyLength() {
        byte length = this.readValue();
        return (int) length;
    }

    private Boolean[] readNextKey() {
        int endIndex = currentIndex + nextKeyLength;
        Boolean[] byteValues = Arrays.copyOfRange(this.bools, currentIndex, endIndex);

        currentIndex = endIndex;

        return byteValues;
    }

    private boolean isItEnd() {

        Boolean[] bits = Arrays.copyOfRange(this.bools, currentIndex, currentIndex + 16);
        for (boolean b : bits) {
            if (b) {
                return false;
            }
        }
        currentIndex += 16;
        return true;
    }

    private void swapBits(boolean[] bitsToProcess, int startAt) {
        for (int j = 8; j > 0; --j) {
            this.bools[startAt - j] = bitsToProcess[j - 1];
        }
    }

    public byte[] convertByteObjectArray(List<Byte> bytes)
    {
        byte[] ret = new byte[bytes.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = bytes.get(i);
        }
        return ret;
    }

}
