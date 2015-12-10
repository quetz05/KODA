package es.elka.koda.app.file;

import java.util.stream.Stream;

public class PgmFileToEncode extends AbstractFileToEncode implements FileToEncode {
    private final int LINES_CONTAINES_HEADER = 4;

    public PgmFileToEncode(String path) {
        super(path);
    }

    @Override
    protected Stream<String> ignoreHeader(Stream<String> dataWithHeader) {
        return dataWithHeader.skip(LINES_CONTAINES_HEADER);
    }

}
