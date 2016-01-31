package es.elka.koda.app.decoder;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DecoderTest {

    @Test
    public void isWrapperHashCodeCorrect(){
        Boolean[] first = { true, true, false, false};
        Boolean[] second = { true, true, false, false};

        CodeWrapper firstElo = new CodeWrapper(first);
        CodeWrapper secondElo = new CodeWrapper(second);


        assertEquals(firstElo.hashCode(), secondElo.hashCode());
    }

    @Test
    public void isWrapperEditedHashCodeCorrect(){
        Boolean[] first = { true, true, false, true};
        Boolean[] second = { true, true, false, false};

        second[3] = true;
        CodeWrapper firstElo = new CodeWrapper(first);
        CodeWrapper secondElo = new CodeWrapper(second);

        assertEquals(firstElo.hashCode(), secondElo.hashCode());
    }

    @Test
    public void doesItReturnFromHashMap(){
        Boolean[] first = { true, true, false, true};
        Boolean[] second = { true, true, false, false};


        CodeWrapper firstElo = new CodeWrapper(first);
        CodeWrapper secondElo = new CodeWrapper(second);

        Map<CodeWrapper, Byte> map = new HashMap<>();
        map.put(firstElo, new Byte("0"));
        map.put(secondElo, new Byte("1"));
        Boolean[] dupa = { true, true, false, false};
        CodeWrapper trzy = new CodeWrapper(dupa);

        assertTrue(secondElo.equals(trzy));
        assertNotNull(map.get(new CodeWrapper(new Boolean[]{ true, true, false, false})));
    }
}
