package es.elka.koda.app.coder;

import es.elka.koda.app.algorithm.HuffmanAlgorithm;
import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * Created by servlok on 24.01.16.
 */
public class CoderTest {
    private HuffmanAlgorithm algorithm = new HuffmanAlgorithm();
    private Coder coder = new Coder(algorithm);

    @Test
    public void encode_listWithOnlyOneElement_Process() {
        List<BitSet> bytes = coder.encode(Arrays.asList((byte) 120));

        System.out.println(bytes);
    }
}
