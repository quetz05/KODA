package es.elka.koda.app.file;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Abstrakcyjna klasa pliku wczytywanego do kodowani.
 * Definiuje operacje wspólne dla każdego formatu pliku wczytywanego do kodowania.
 *
 * @author servlok
 */
public abstract class AbstractFileToEncode implements FileToEncode {
    private Path path;

    /**
     * Konstruktor klasy.
     * @param path ścieżka do pliku
     */
    public AbstractFileToEncode(String path) {
        this.path = Paths.get(path);
    }

    @Override
    public Path getPath() {
        return this.path;
    }

    /**
     * Leniwe wczytanie wszystkich linijek z pliku
     * @return strumien ciągu znaków reprezentujących kolejne linijki tesktu
     * @throws IOException
     */
    protected List<Byte> loadAllData() throws IOException {
        byte[] dataWithHeader = Files.readAllBytes(path);
        return Arrays.asList(ArrayUtils.toObject(dataWithHeader));
    }

    /**
     * Domyślna metoda pobierania danych z pliku. Podklasa wykorzystuje tę klasę jako szkielet wywołania,
     * implementując jedynie abstrakcyjną metodę ignoreHeader, która zależna jest od samego formatu.
     * @return lista bajtów danych z pliku bez nagłówka
     * @throws IOException
     */
    @Override
    public List<Byte> loadData() throws IOException {
        List<Byte> dataWithHeader = loadAllData();
        return ignoreHeader(dataWithHeader);
    }

    /**

    /**
     * Abstrakcyjna metoda, która powinna być implementowana w każdej podklasie (ignorowanie nagłówka jest zależne
     * od formatu pliku)
     * @param dataWithHeader dane z nagłówkiem
     * @return dane bez nagłówka
     */
    protected abstract List<Byte> ignoreHeader(List<Byte> dataWithHeader);


}
