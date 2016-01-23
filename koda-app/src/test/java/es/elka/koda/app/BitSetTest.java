package es.elka.koda.app;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;

/**
 * Created by servlok on 22.01.16.
 */
public class BitSetTest {

    @Test
    public void test1() {
        BitSet bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(0);
        bitSet.set(4);
        bitSet.set(8);
        System.out.println(bitSet);

        //10011 = 1 + 2 + 16 = 19
        System.out.println(bitSet.get(11));
        System.out.println(bitSet.get(10));

        //8 7 6 5 4 3 2 1 | 16 15 14 13 12 11 10 9 | 24 23 22 21 20 19 18 17
        System.out.println(bitSet.toByteArray().length);
        System.out.println(bitSet.toByteArray()[0]);
        System.out.println(bitSet.toByteArray()[1]);

        System.out.println(bitSet.length());

        BitSet bitSet1 = new BitSet();
        System.out.println(bitSet1.length());
    }

    @Test
    public void test2() throws IOException {
        BitSet bitSet = new BitSet();
        bitSet.set(7);
//        bitSet.set(0);
//        bitSet.set(9);
//        bitSet.set(10);


        Files.write(Paths.get("test.txt"), bitSet.toByteArray());

    }

    @Test
    public void test3() {
        BitSet bitSet = new BitSet();

        bitSet.set(1, true);

        System.out.println(bitSet.size());
    }
}
