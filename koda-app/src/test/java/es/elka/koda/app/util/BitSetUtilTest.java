package es.elka.koda.app.util;

import org.junit.Test;

import java.util.BitSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by servlok on 23.01.16.
 */
public class BitSetUtilTest {


    @Test
    public void moveBitsToLeftSideOfByte_1shouldBeOnTheLeftSide_Process() {
        BitSet bitSet = new BitSet();
        bitSet.set(0);

        byte data = BitSetUtil.moveBitsToLeftSideOfByte(bitSet);

        assertEquals(-128, data);
    }

    @Test
    public void moveBitsToLeftSideOfByte_shouldBeStill0_Process() {
        BitSet bitSet = new BitSet();

        byte data = BitSetUtil.moveBitsToLeftSideOfByte(bitSet);

        assertEquals(0, data);
    }


    @Test(expected = IllegalArgumentException.class)
    public void moveBitsToLeftSideOfByte_shouldBeGreaterThan8Length_ThrowIllegalArgumentException() {
        BitSet bitSet = new BitSet();
        bitSet.set(8);

        byte data = BitSetUtil.moveBitsToLeftSideOfByte(bitSet);

    }

}
