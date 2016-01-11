package es.elka.koda.app.file;

import org.apache.commons.lang3.ArrayUtils;

import java.util.BitSet;
import java.util.List;
import java.util.Map;

final class MapToArrayHelper {

    private static byte[] TERMINAL_SEPARATOR = {0};
    private Map<Byte, BitSet> dictionary;

    byte[] execute(List<BitSet> dataToMap) {
        byte[] mappedData = new byte[0];
        for (BitSet bits : dataToMap) {
            byte[] dataToAdd = bits.toByteArray();
            mappedData = ArrayUtils.addAll(mappedData, dataToAdd);
        }
        return mappedData;
    }

    byte[] execute(Map<Byte, BitSet> dictionary) {
        this.dictionary = dictionary;

        byte[] mappedData = new byte[0];
        for (Byte sign : dictionary.keySet()) {
            byte[] parsedPair = parsePair(sign);
            mappedData = ArrayUtils.addAll(mappedData, parsedPair);
        }
        mappedData = ArrayUtils.addAll(mappedData, TERMINAL_SEPARATOR);
        return mappedData;
    }

    private byte[] parsePair(Byte sign) {
        byte[] data = new byte[0];
        data = ArrayUtils.addAll(data, sign);
        data = ArrayUtils.addAll(data, (byte) dictionary.get(sign).length());
        data = ArrayUtils.addAll(data, dictionary.get(sign).toByteArray());
        return data;
    }

}
