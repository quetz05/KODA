package es.elka.koda.app.util;

import java.util.BitSet;
import java.util.stream.IntStream;

/**
 * Created by servlok on 23.01.16.
 */
public class BitSetUtil {

    private static int BITS_PER_BYTE = 8;

    /**
     * Transformuje bity w BitSets do "lewej" strony, innymi słowy dla bajta
     * 00011101
     * wynikiem transformacji bedzie
     * 11101000
     *
     * @return bajt po transformacji
     * @throws IllegalArgumentException jezeli liczba bitow argumentow bedzie wieksza od 8
     */
    public static byte moveBitsToLeftSideOfByte(BitSet bits) {
        if (bits.length() > BITS_PER_BYTE) {
            throw new IllegalArgumentException("Invalid number of bits: should be less-equal than " + BITS_PER_BYTE);
        }

        int[] bitsIndexes = bits.stream()
                .map(i -> i + BITS_PER_BYTE - bits.length())
                .toArray();

        bits.clear();

        IntStream.of(bitsIndexes).forEach(bits::set);

        return (bits.length() == 0 ? 0 : bits.toByteArray()[0]);
    }

    public static byte moveBitsToLeftSideOfByte(Byte data) {
        return moveBitsToLeftSideOfByte(byteToBitSet(data));
    }

    public static BitSet byteToBitSet(byte data) {
        byte[] dataArray = new byte[1];
        dataArray[0] = data;
        return BitSet.valueOf(dataArray);
    }
}
