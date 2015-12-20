package es.elka.koda.app.file;

import java.util.stream.Stream;

/**
 * Plik w formacie *.pgm do wczytywania danych bez nagłówka
 */
public class PgmFileToEncode extends AbstractFileToEncode implements FileToEncode {
    private final int LINES_CONTAINES_HEADER = 4;

    public PgmFileToEncode(String path) {
        super(path);
    }

    /**
     * Wykorzystywana w metodzie szkieletowej zaimplementowanej w AbstractFileToEncode.
     * Pomija 4 pierwsze linie pliku do zakodowania zawierające nagłówek.
     *
     * @param dataWithHeader dane z nagłówkiem
     * @return dane bez nagłowka
     * @see AbstractFileToEncode#loadData()
     */
    @Override
    protected Stream<String> ignoreHeader(Stream<String> dataWithHeader) {
        return dataWithHeader.skip(LINES_CONTAINES_HEADER);
    }

}
