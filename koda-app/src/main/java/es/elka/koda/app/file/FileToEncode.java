package es.elka.koda.app.file;

import java.io.IOException;
import java.util.List;

/**
 * Interfejs źródła pliku do zaszyfrowania. Zaleca się aby przy implementacji tego interfejsu skorzystać z
 * metody abstrakcyjnej AbstracktFileToEncode
 *
 * @see AbstractFileToEncode
 */
public interface FileToEncode {
    /**
     * Wczytywanie danych BEZ nagłówka w formie bajtów. Zaleca się wykorzystywanie metody szkieletowej zawartej
     * tutaj:
     * @see AbstractFileToEncode#loadData()
     * @return
     * @throws IOException
     */
    List<Byte> loadData() throws IOException;
}
