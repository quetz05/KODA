package es.elka.koda.app.file;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class PgmFileToEncodeTest {

    /*
        Moje wlasne testy funkcji
        Zakladaja ze w okreslonej lokalizacji znajduja sie wybrane pliki
        stad domyslnie ignorowane
     */
    @Test
    @Ignore
    public void shouldTestIfWork() throws IOException {
        String path = "/home/servlok/IdeaProjects/KODA/datatest/8x8.pgm";
        FileToEncode fileToEncode = new PgmFileToEncode(path);
        List<Byte> data = fileToEncode.loadData();

        System.out.print(data);
    }
}
