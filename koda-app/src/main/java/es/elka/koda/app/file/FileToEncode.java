package es.elka.koda.app.file;

import java.io.IOException;
import java.util.List;

public interface FileToEncode {
    List<Byte> loadData() throws IOException;
}
