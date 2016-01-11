package es.elka.koda.app.file;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class EncodedFileTest {
    /*
        Moje wlasne testy funkcji
        Zakladaja ze w okreslonej lokalizacji da się dodać plik
        stad domyslnie ignorowane
     */
    @Test
    @Ignore
    public void shouldWriteTestFileWithoutDictionary() throws IOException {
        String fileName = "test";
        EncodedFile file = new EncodedFileImpl(fileName);
        List<BitSet> sampleData = prepareData();
        System.out.println(sampleData);
        file.save(sampleData, new HashMap<>());
    }

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
