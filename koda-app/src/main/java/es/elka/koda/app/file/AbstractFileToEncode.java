package es.elka.koda.app.file;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractFileToEncode implements FileToEncode {
    private Path path;

    public AbstractFileToEncode(String path) {
        this.path = Paths.get(path);
    }

    protected Stream<String> loadAllData() throws IOException {
        return Files.lines(path);
    }

    @Override
    public List<Byte> loadData() throws IOException {
        Stream<String> dataWithHeader = loadAllData();
        Stream<String> dataWithoutHeader = ignoreHeader(dataWithHeader);
        return mapToBytes(dataWithoutHeader);
    }

    private List<Byte> mapToBytes(Stream<String> dataWithoutHeader) {
        return dataWithoutHeader
                .flatMap(s -> Stream.of(ArrayUtils.toObject(s.getBytes())))
                .collect(Collectors.toList());
    }

    protected abstract Stream<String> ignoreHeader(Stream<String> dataWithHeader);


}
