package es.elka.koda.app.file;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Plik w formacie *.pgm do wczytywania danych bez nagłówka
 */
public class PgmFileToEncode extends AbstractFileToEncode implements FileToEncode {
    private final int LINES_CONTAINES_HEADER = 4;
    private final Byte NEXT_LINE_SIGN = 0x0A;
    private boolean takeAllNext = false;
    private int currentLinesContainsHeader = 0;

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
    protected List<Byte> ignoreHeader(List<Byte> dataWithHeader) {
        this.takeAllNext = false;
        return dataWithHeader.stream()
                .filter(this::checkSignAndUpdate)
                .collect(Collectors.toList());
    }

    private boolean checkSignAndUpdate(Byte b) {
        if (takeAllNext) return true;

        if (b.equals(NEXT_LINE_SIGN)) {
            currentLinesContainsHeader++;
        }
        if (currentLinesContainsHeader == LINES_CONTAINES_HEADER) {
            takeAllNext = true;
        }
        return false;
    }

}
