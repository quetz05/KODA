package es.elka.koda.app.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class EncodedFileTest {

    private List<BitSet> prepareData() {
        List<Integer> integers = Arrays.asList(
                119, 97, 107, 97, 32, 119, 97, 107, 97, 32, 101, 32, 101, 32, 116, 104, 105,
                115, 32, 116, 105, 109, 101, 32, 102, 111, 114, 32, 97, 102, 114, 105, 99, 97);
        List<BitSet> mappedData = new ArrayList<>();
        for (Integer dataToAdd : integers) {
            BitSet bitSet = BitSet.valueOf(new byte[]{dataToAdd.byteValue()});
            mappedData.add(bitSet);
        }

        return mappedData;
    }
}
