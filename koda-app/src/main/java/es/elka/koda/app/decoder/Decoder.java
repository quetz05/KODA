package es.elka.koda.app.decoder;

import java.util.*;

public class Decoder {
    private final byte[] bytes;
    private final Map<MojMap, Byte> dictionary;
    private int currentIndex = 0;
    private int nextKeyLength;
    private Boolean[] bools;

    public Decoder(byte[] bytes) {
        this.bytes = bytes;
        dictionary = new HashMap<>();
    }

    public void decode() {

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
            dictionary.put(new MojMap(nextKey), value);
        }
        this.readKeyNumber();
        this.decodeMeDatShit();

        System.out.println("Bajlando");
    }

    private void readKeyNumber() {
        currentIndex = currentIndex + 32;
        //po co mię ta liczba elementow??
    }

    private byte[] decodeMeDatShit() {
        ArrayList<Byte> decodedBytes = new ArrayList<>();
        ArrayList<Boolean> values = new ArrayList<>();

        for (int i = this.currentIndex; i < this.bools.length; ++i) {
            values.add(this.bools[i]);
            Boolean[] op = values.toArray(new Boolean[values.size()]);
            MojMap mm = new MojMap(op);


//            Byte decodedByte = dictionary.get(mm);
//            if (decodedByte != null) {
//
//                decodedBytes.add(decodedByte);
//                values.clear();
//            }
        }

        return new byte[0];
    }

    private byte readValue() {
        int endIndex = currentIndex + 8;

        Boolean[] bitValues = Arrays.copyOfRange(this.bools, currentIndex, endIndex);

        BitSet bitsetBits = new BitSet(8);

        for (int i = 0; i < bitValues.length; ++i) {
            if (bitValues[i]) bitsetBits.set(7 - i);
        }

        byte[] aaaa = bitsetBits.toByteArray();

        currentIndex = endIndex;

        if (bitsetBits.length() == 0)
            return 0;
        else

            return aaaa[0];
    }

    private int readKeyLength() {
        byte length = this.readValue();

        int iLen = length;
        return iLen;
    }

    private Boolean[] readNextKey() {
        int endIndex = currentIndex + nextKeyLength;
        Boolean[] bbb = Arrays.copyOfRange(this.bools, currentIndex, endIndex);

        currentIndex = endIndex;

        return bbb;
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

}
