package es.elka.koda.app.file;

import es.elka.koda.app.util.BitSetUtil;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by servlok on 23.01.16.
 */
public class BitSetToByteArrayBuilder {
    private final static int BITS_PER_BYTE = 8;
    private BitSet lastBits = new BitSet();

    private int lastBitsLength = 0;

    private List<Byte> bytes = new ArrayList<>();

    /**
     * To samo co #addBits 2 argumentowe, z ta roznica, ze zakladamy, ze bits.length() == bitsLength
     * zatem w konsekwencji dlugosc jest rownoznaczna z ostatnim wystapieniem 1, zatem zarowno dla ciagu
     * 00110101 jak i 110101, dlugosc jest rowna 5
     *
     * @param bits
     * @return
     */
    public BitSetToByteArrayBuilder addBits(BitSet bits) {
        addBits(bits, bits.length());
        return this;
    }

    /**
     * Metoda dodaje bity i w razie koniecznosc formuluje dodane bity w bajt i dodaje do listy bajtow.
     * Warto wspomniec ze jako argument poza BitSetem podawany jest rowniez dlugosc BitSetu.
     * Wynika to z faktu, że metoda {java.util.BitSet#length} zwraca dlugosc z uwzglednieniem
     * ostatniego wystapienia 1, zatem dla ciagu
     * 00110101
     * metoda length zwroci 5. stad aby wynik byl poprawny nalezy podac odpowiednia dlugosc, czyli dla
     * przykladu powyzej wywolanie funkcji powinno miec postac addBits(bits,7)
     *
     * W przypadku gdy liczba bitow jest wieksza od 8, na poczatku dodawane jest 8 bitow pierwszych, a nastepnie
     * dodawana reszta (rekurencyjnie)
     *
     * @param bits
     * @param bitsLength dlugosc bitow z uwzglednieniem 0 poczatkowych
     * @return
     * @throws IllegalArgumentException jezeli dlugosc podana jako drugi argument jest albo wieksza od 8 albo
     *                                  jest mniejsza od wartosci zwroconej przez {java.util.BitSet#length}
     */
    public BitSetToByteArrayBuilder addBits(BitSet bits, int bitsLength) {
        if (bits.length() > bitsLength) {
            throw new IllegalArgumentException("Argument bitslength has to be larger value returned by addBits.length()!");
        }

        if (bitsLength > BITS_PER_BYTE) {
            BitSet firstEightBits = bits.get(bitsLength - BITS_PER_BYTE, bitsLength);
            BitSet restBits = bits.get(0, bitsLength - BITS_PER_BYTE);
            this.addBits(firstEightBits, BITS_PER_BYTE);
            this.addBits(restBits, bitsLength - BITS_PER_BYTE);
        }

        updateBytesAndAddToLastBits(bits, bitsLength);

        return this;
    }

    public BitSetToByteArrayBuilder addByte(byte data) {
        addBits(BitSetUtil.byteToBitSet(data), BITS_PER_BYTE);
        return this;
    }

    public BitSetToByteArrayBuilder addBytes(byte... data) {
        for (byte b : data) {
            addByte(b);
        }
        return this;
    }

    private void updateBytesAndAddToLastBits(BitSet bitSet, int bitsLength) {
        updateLastBits(bitSet, bitsLength);
        lastBitsLength += bitsLength;

        if (lastBitsLength >= BITS_PER_BYTE) {
            lastBitsLength -= BITS_PER_BYTE;
            takeByteFromBits(bitsLength);
        }

    }

    private void takeByteFromBits(int bitsLength) {
        byte value = (byte) lastBits.stream()
                .filter(i -> i >= lastBitsLength)
                .map(i -> i - lastBitsLength)
                .map(i -> (int) Math.pow(2, i))
                .sum();

        bytes.add(value);

        lastBits.clear(lastBitsLength, lastBitsLength + BITS_PER_BYTE);

    }

    private void updateLastBits(BitSet bitSet, int bitsLength) {
        int[] movedBitsIndexes = lastBits.stream()
                .map(i -> i + bitsLength)
                .toArray();

        lastBits.clear();

        IntStream.of(movedBitsIndexes).forEach(lastBits::set);

        bitSet.stream().forEach(lastBits::set);
    }

    /**
     * build powinien byc metoda konczaca wprowadzanie bitow, wypelnia do konca ciag bitow albo bitami zerowymi
     * (dla value == false) albo jedynkami (dla value == true), dodaje ten ciag jako kolejny bajt, i zwraca w ten
     * sposob otrzymana liste bajtow
     *
     * @param value
     * @return
     */
    public List<Byte> build(boolean value) {
        if (lastBitsLength == 0)
            return bytes;

        if (value) fillWithOnes();
        else fillWithZeros();
        List<Byte> bytes = this.bytes;

        this.clear();
        return bytes;
    }

    private void fillWithOnes() {
        BitSet onlyOneSet = new BitSet();
        onlyOneSet.set(0, BITS_PER_BYTE - lastBitsLength);
        addBits(onlyOneSet);
    }

    private void fillWithZeros() {
        BitSet onlyZeroSet = new BitSet();
        addBits(onlyZeroSet, BITS_PER_BYTE - lastBitsLength);
    }

    private void clear() {
        this.bytes = new ArrayList<>();
        this.lastBitsLength = 0;
        this.lastBits.clear();
    }


    public BitSet getLastBits() {
        return lastBits;
    }

    public List<Byte> getBytes() {
        return bytes;
    }

    public int getLastBitsLength() {
        return lastBitsLength;
    }
}
