package es.elka.koda.app.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    protected Stream<String> loadAllData() throws IOException {
        return Files.lines(path, StandardCharsets.ISO_8859_1);
    }

    /**
     * Domyślna metoda pobierania danych z pliku. Podklasa wykorzystuje tę klasę jako szkielet wywołania,
     * implementując jedynie abstrakcyjną metodę ignoreHeader, która zależna jest od samego formatu.
     * @return lista bajtów danych z pliku bez nagłówka
     * @throws IOException
     */
    @Override
    public List<Byte> loadData() throws IOException {
        Stream<String> dataWithHeader = loadAllData();
        Stream<String> dataWithoutHeader = ignoreHeader(dataWithHeader);
        return mapToBytes(dataWithoutHeader);
    }

    /**
     * Mapowanie danych z listy ciągów znaków na listę bajtów
     * @param dataWithoutHeader
     * @return
     */
    private List<Byte> mapToBytes(Stream<String> dataWithoutHeader) {
        return dataWithoutHeader
                .flatMap(s -> s.chars().mapToObj(i -> (byte) i))
                .collect(Collectors.toList());
    }

    /**
     * Abstrakcyjna metoda, która powinna być implementowana w każdej podklasie (ignorowanie nagłówka jest zależne
     * od formatu pliku)
     * @param dataWithHeader dane z nagłówkiem
     * @return dane bez nagłówka
     */
    protected abstract Stream<String> ignoreHeader(Stream<String> dataWithHeader);


}
