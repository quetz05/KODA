package es.elka.koda.app.file;

import org.apache.commons.lang3.ArrayUtils;

import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

final class MapToArrayHelper {

    private final static BitSet ZERO_BIT = new BitSet();
    private final static BitSet ONE_BIT = new BitSet();

    static {
        ONE_BIT.set(0);
    }

    BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();
    private Map<Byte, BitSet> dictionary;

    byte[] execute(List<BitSet> dataToMap) {
        dataToMap.stream().forEach(bitSetToByteArrayBuilder::addBits);
        return ArrayUtils.toPrimitive(
                (Byte[]) bitSetToByteArrayBuilder.build(false).toArray()
        );
    }

    byte[] execute(Map<Byte, BitSet> dictionary, int size) {
        this.dictionary = dictionary;

        dictionary.keySet().stream().forEach(this::parsePair);

        return ArrayUtils.toPrimitive((Byte[])
                bitSetToByteArrayBuilder.addBits(ZERO_BIT, 1)
                        .addBytes(ByteBuffer.allocate(4).putInt(size).array())
                        .build(false).toArray()
        );

    }

    private void parsePair(Byte sign) {
        bitSetToByteArrayBuilder.addByte(sign)
                .addByte(dictionary.get(sign).toByteArray()[0])
                .addBits(ONE_BIT, 1);
    }


//    byte[] execute(Map<Byte, BitSet> dictionary) {
//        this.dictionary = dictionary;
//
//        byte[] mappedData = dictionary.keySet().stream()
//                .map(this::parsePair)
//                .reduce(new byte[0], ArrayUtils::addAll);
//
//        mappedData = ArrayUtils.addAll(mappedData, TERMINAL_SEPARATOR);
//
//        return mappedData;
//    }
//
//    private byte[] parsePair(Byte sign) {
//        byte[] data = new byte[0];
//        data = ArrayUtils.addAll(data, sign);
//        data = ArrayUtils.addAll(data, dictionary.get(sign).toByteArray()[0]);
//        return data;
//    }

}
