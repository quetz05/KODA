package es.elka.koda.app.file;

import org.junit.Test;

import java.util.BitSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by servlok on 23.01.16.
 */
public class BitSetToByteArrayBuilderTest {

    @Test(expected = IllegalArgumentException.class)
    public void addBits_bitSetIsIgnoredIfBitSetLengthIsMoreThanNumberBitsInByte_IllegalArgumentExceptionThrown() {
        BitSet bitSet = new BitSet();
        bitSet.set(8);

        BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();
        bitSetToByteArrayBuilder.addBits(bitSet, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addBits_bitsLengthCantBeLessThaBitSetlength_IllegalArgumentExceptionThrown() {
        BitSet bitSet = new BitSet();
        bitSet.set(1);

        BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();
        bitSetToByteArrayBuilder.addBits(bitSet, 0);
    }

    @Test
    public void addBits_bitSetsIsAssignedToInternalStateBitSet_Proccess() {
        BitSet bitSet = new BitSet();
        bitSet.set(0);
        bitSet.set(3);

        BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();
        bitSetToByteArrayBuilder.addBits(bitSet, 4);

        assertEquals(bitSet, bitSetToByteArrayBuilder.getLastBits());
    }

    @Test
    public void addBits_bitSetInArgumentAndLastBitSetIsMerged_Proccess() {
        BitSet bitSet = new BitSet();
        bitSet.set(0);
        bitSet.set(3);
        BitSet secondBitSet = new BitSet();
        secondBitSet.set(0);
        secondBitSet.set(2);

        BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();
        bitSetToByteArrayBuilder.addBits(bitSet, 4).addBits(secondBitSet, 3);

        BitSet expectedBitSet = new BitSet();
        expectedBitSet.set(0);
        expectedBitSet.set(2);
        expectedBitSet.set(3);
        expectedBitSet.set(6);
        assertEquals(expectedBitSet, bitSetToByteArrayBuilder.getLastBits());
    }

    @Test
    public void addBits_addTwiceToSummaryLength8AndThenLastBitsShouldHasLength0_Proccess() {
        BitSet bitSet = new BitSet();
        bitSet.set(0);
        bitSet.set(1);
        bitSet.set(2);
        bitSet.set(3);
        BitSet secondBitSet = new BitSet();
        secondBitSet.set(0);
        secondBitSet.set(1);
        secondBitSet.set(2);

        BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();
        bitSetToByteArrayBuilder.addBits(bitSet, 4).addBits(secondBitSet, 4);

        BitSet expectedBitSet = new BitSet();
        assertEquals(expectedBitSet, bitSetToByteArrayBuilder.getLastBits());
        assertEquals(0, bitSetToByteArrayBuilder.getLastBitsLength());
        assertEquals(1, bitSetToByteArrayBuilder.getBytes().size());
        //11110111 (2) = 1 + 2 + 4 + 16 + 32 + 64 - 128 = - 9
        assertEquals(Byte.valueOf((byte) -9), bitSetToByteArrayBuilder.getBytes().get(0));
    }

    @Test
    public void addBits_bitSetInArgumentAndLastBitSetIsLargerThanByteSizeAndAddedToByteList_Proccess() {
        BitSet bitSet = new BitSet();
        bitSet.set(0);
        bitSet.set(2);
        bitSet.set(5);
        BitSet secondBitSet = new BitSet();
        secondBitSet.set(0);
        secondBitSet.set(1);
        secondBitSet.set(4);

        BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();
        bitSetToByteArrayBuilder.addBits(bitSet, 6).addBits(secondBitSet, 5);

        BitSet expectedBitSet = new BitSet();
        expectedBitSet.set(0);
        expectedBitSet.set(1);
        expectedBitSet.clear(3);

        //011
        assertEquals(expectedBitSet, bitSetToByteArrayBuilder.getLastBits());

        //length(011) = 3
        assertEquals(3, bitSetToByteArrayBuilder.getLastBitsLength());

        //100101 (2) = -128 + 16 + 4 +2 (10) = -106 (10)
        assertEquals(new Byte((byte) -106), bitSetToByteArrayBuilder.getBytes().get(0));
    }

    @Test
    public void addBits_AddConstantly3Length001BitsAndAtTheEndShouldBe3BytesWithZeroLengthBits_Proccess() {
        BitSet bitSet = new BitSet();
        bitSet.set(0);

        BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();
        bitSetToByteArrayBuilder.addBits(bitSet, 3);


        //////////////////////FIRST ITERATION
        BitSet expectedBitSetAfter1Iteration = new BitSet();
        expectedBitSetAfter1Iteration.set(0);

        //001
        assertEquals(expectedBitSetAfter1Iteration, bitSetToByteArrayBuilder.getLastBits());
        //length(001) = 3
        assertEquals(3, bitSetToByteArrayBuilder.getLastBitsLength());
        assertEquals(0, bitSetToByteArrayBuilder.getBytes().size());

        //////////////////////SECOND ITERATION
        BitSet expectedBitSetAfter2Iteration = new BitSet();
        expectedBitSetAfter2Iteration.set(0);
        expectedBitSetAfter2Iteration.set(3);
        bitSetToByteArrayBuilder.addBits(bitSet, 3);

        //001001
        assertEquals(expectedBitSetAfter2Iteration, bitSetToByteArrayBuilder.getLastBits());
        //length(001001) = 6
        assertEquals(6, bitSetToByteArrayBuilder.getLastBitsLength());
        assertEquals(0, bitSetToByteArrayBuilder.getBytes().size());

        //////////////////////THIRD ITERATION
        BitSet expectedBitSetAfter3Iteration = new BitSet();
        expectedBitSetAfter3Iteration.set(0);
        bitSetToByteArrayBuilder.addBits(bitSet, 3);

        //1
        assertEquals(expectedBitSetAfter3Iteration, bitSetToByteArrayBuilder.getLastBits());
        //length(1) = 1
        assertEquals(1, bitSetToByteArrayBuilder.getLastBitsLength());
        assertEquals(1, bitSetToByteArrayBuilder.getBytes().size());
        //00100100 (2) = 4 +  32 = 36
        assertEquals(Byte.valueOf((byte) 36), bitSetToByteArrayBuilder.getBytes().get(0));

        //////////////////////FORTH ITERATION
        BitSet expectedBitSetAfter4Iteration = new BitSet();
        expectedBitSetAfter4Iteration.set(0);
        expectedBitSetAfter4Iteration.set(3);
        bitSetToByteArrayBuilder.addBits(bitSet, 3);

        //1001
        assertEquals(expectedBitSetAfter4Iteration, bitSetToByteArrayBuilder.getLastBits());
        //length(1001) = 4
        assertEquals(4, bitSetToByteArrayBuilder.getLastBitsLength());
        assertEquals(1, bitSetToByteArrayBuilder.getBytes().size());
        //00100100 (2) = 4 +  32 = 36
        assertEquals(Byte.valueOf((byte) 36), bitSetToByteArrayBuilder.getBytes().get(0));

        //////////////////////FIFTH ITERATION
        BitSet expectedBitSetAfter5Iteration = new BitSet();
        expectedBitSetAfter5Iteration.set(0);
        expectedBitSetAfter5Iteration.set(3);
        expectedBitSetAfter5Iteration.set(6);
        bitSetToByteArrayBuilder.addBits(bitSet, 3);

        //1001001
        assertEquals(expectedBitSetAfter5Iteration, bitSetToByteArrayBuilder.getLastBits());
        //length(1001001) = 7
        assertEquals(7, bitSetToByteArrayBuilder.getLastBitsLength());
        assertEquals(1, bitSetToByteArrayBuilder.getBytes().size());
        //00100100 (2) = 4 +  32 = 36
        assertEquals(Byte.valueOf((byte) 36), bitSetToByteArrayBuilder.getBytes().get(0));

        //////////////////////SIXTH ITERATION
        BitSet expectedBitSetAfter6Iteration = new BitSet();
        expectedBitSetAfter6Iteration.set(0);
        bitSetToByteArrayBuilder.addBits(bitSet, 3);

        //01
        assertEquals(expectedBitSetAfter6Iteration, bitSetToByteArrayBuilder.getLastBits());
        //length(01) = 2
        assertEquals(2, bitSetToByteArrayBuilder.getLastBitsLength());
        assertEquals(2, bitSetToByteArrayBuilder.getBytes().size());
        //00100100 (2) = 4 + 32 = 36
        assertEquals(Byte.valueOf((byte) 36), bitSetToByteArrayBuilder.getBytes().get(0));
        //10010010 (2) = 2 + 16 - 128 = - 110
        assertEquals(Byte.valueOf((byte) -110), bitSetToByteArrayBuilder.getBytes().get(1));

        //////////////////////SEVENTH ITERATION
        BitSet expectedBitSetAfter7Iteration = new BitSet();
        expectedBitSetAfter7Iteration.set(0);
        expectedBitSetAfter7Iteration.set(3);
        bitSetToByteArrayBuilder.addBits(bitSet, 3);

        //01001
        assertEquals(expectedBitSetAfter7Iteration, bitSetToByteArrayBuilder.getLastBits());
        //length(01001) = 5
        assertEquals(5, bitSetToByteArrayBuilder.getLastBitsLength());
        assertEquals(2, bitSetToByteArrayBuilder.getBytes().size());
        //00100100 (2) = 4 + 32 = 36
        assertEquals(Byte.valueOf((byte) 36), bitSetToByteArrayBuilder.getBytes().get(0));
        //10010010 (2) = 2 + 16 - 128 = - 110
        assertEquals(Byte.valueOf((byte) -110), bitSetToByteArrayBuilder.getBytes().get(1));

        //////////////////////EIGHTH ITERATION
        BitSet expectedBitSetAfter8Iteration = new BitSet();
        bitSetToByteArrayBuilder.addBits(bitSet, 3);

        //<empty>
        assertEquals(expectedBitSetAfter8Iteration, bitSetToByteArrayBuilder.getLastBits());
        //length(<empty>) = 0
        assertEquals(0, bitSetToByteArrayBuilder.getLastBitsLength());
        assertEquals(3, bitSetToByteArrayBuilder.getBytes().size());
        //00100100 (2) = 4 + 32 = 36
        assertEquals(Byte.valueOf((byte) 36), bitSetToByteArrayBuilder.getBytes().get(0));
        //10010010 (2) = 2 + 16 - 128 = - 110
        assertEquals(Byte.valueOf((byte) -110), bitSetToByteArrayBuilder.getBytes().get(1));
        //01001001 (2) = 1 + 8 + 64 = 73
        assertEquals(Byte.valueOf((byte) 73), bitSetToByteArrayBuilder.getBytes().get(2));

    }

    @Test
    public void addByte_lastBitsShouldBe0AndBytesShouldAddByteValue_Process() {
        BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();

        byte data = (byte) 64;

        bitSetToByteArrayBuilder.addByte(data);
        assertEquals(0, bitSetToByteArrayBuilder.getLastBitsLength());
        assertEquals(new BitSet(), bitSetToByteArrayBuilder.getLastBits());
        assertEquals(1, bitSetToByteArrayBuilder.getBytes().size());
        assertEquals(Byte.valueOf(data), bitSetToByteArrayBuilder.getBytes().get(0));
    }

    @Test
    public void addByte_lastBitsShouldBeLast3BitsOfByte_Process() {
        BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();
        BitSet bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(4);
        bitSetToByteArrayBuilder.addBits(bitSet, 5);
        //10010

        byte data = (byte) 73;

        bitSetToByteArrayBuilder.addByte(data);
        //10010010 | 01001

        //5
        assertEquals(5, bitSetToByteArrayBuilder.getLastBitsLength());

        BitSet expectedBitSet = new BitSet();
        expectedBitSet.set(0);
        expectedBitSet.set(3);

        assertEquals(expectedBitSet, bitSetToByteArrayBuilder.getLastBits());

        assertEquals(1, bitSetToByteArrayBuilder.getBytes().size());

        //10010010 (2) = 2 + 16 - 128 = -110
        assertEquals(Byte.valueOf((byte) -110), bitSetToByteArrayBuilder.getBytes().get(0));
    }

    @Test
    public void toBuild_ShouldReturnWithoutChange_Process() {
        BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();
        bitSetToByteArrayBuilder.build(true);

        assertEquals(new BitSet(), bitSetToByteArrayBuilder.getLastBits());
        assertEquals(0, bitSetToByteArrayBuilder.getLastBitsLength());
        assertEquals(0, bitSetToByteArrayBuilder.getBytes().size());
    }

    @Test
    public void toBuild_ShouldFillBitSetWithOnes_Process() {
        BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();
        BitSet bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(4);

        List<Byte> bytes = bitSetToByteArrayBuilder.addBits(bitSet).build(true);

        assertEquals(new BitSet(), bitSetToByteArrayBuilder.getLastBits());
        assertEquals(0, bitSetToByteArrayBuilder.getLastBitsLength());

        assertEquals(1, bytes.size());
        //10010111 = 1 + 2 + 4 + 16 - 128 = -105
        assertEquals(Byte.valueOf((byte) -105), bytes.get(0));
    }

    @Test
    public void toBuild_ShouldFillBitSetWithZeros_Process() {
        BitSetToByteArrayBuilder bitSetToByteArrayBuilder = new BitSetToByteArrayBuilder();
        BitSet bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(4);

        List<Byte> bytes = bitSetToByteArrayBuilder.addBits(bitSet).build(false);

        assertEquals(new BitSet(), bitSetToByteArrayBuilder.getLastBits());
        assertEquals(0, bitSetToByteArrayBuilder.getLastBitsLength());

        assertEquals(1, bytes.size());
        //10010000 = 16 - 128 = -112
        assertEquals(Byte.valueOf((byte) -112), bytes.get(0));
    }

}
