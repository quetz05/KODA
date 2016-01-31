package es.elka.koda.app.file;

import es.elka.koda.app.algorithm.BitsWrapper;
import org.apache.commons.lang3.ArrayUtils;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

final class MapToArrayHelper {

    private BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();
    private Map<Byte, BitsWrapper> dictionary;
    private byte[] TERMINAL_SIGNS = {0, 0};

    public MapToArrayHelper(Map<Byte, BitsWrapper> dictionary) {
        this.dictionary = dictionary;
    }

    MapToArrayHelper addEncodedData(List<BitsWrapper> dataToMap) {
        dataToMap.stream().forEach(b -> bitSetToByteArrayBuilder.addBits(b.getBitSet(), b.getLength()));
        return this;
    }

    MapToArrayHelper addDictionary(int size) {
        dictionary.keySet().stream().forEach(
                b -> bitSetToByteArrayBuilder.addByte(b)
                        .addByte((byte) dictionary.get(b).getLength())
                        .addBits(dictionary.get(b).getBitSet(), dictionary.get(b).getLength())
        );

        bitSetToByteArrayBuilder.addBytes(TERMINAL_SIGNS)
                .addBytes(ByteBuffer.allocate(4).putInt(size).array());

        return this;
    }

    byte[] toBuild() {
        return ArrayUtils.toPrimitive(
                bitSetToByteArrayBuilder.build(false).toArray(new Byte[bitSetToByteArrayBuilder.getBytes().size()])
        );
    }

//    private void parsePair(Byte sign) {
//        bitSetToByteArrayBuilder.addByte(sign)
//                .addByte(dictionary.get(sign).toByteArray()[0])
//                .addBits(ONE_BIT, 1);
//    }


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
