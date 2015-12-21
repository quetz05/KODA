package es.elka.koda.app;

import es.elka.koda.app.algorithm.HuffmanAlgorithm;
import es.elka.koda.app.coder.Coder;
import es.elka.koda.app.file.EncodedFile;
import es.elka.koda.app.file.EncodedFileImpl;
import es.elka.koda.app.file.FileToEncode;
import es.elka.koda.app.file.PgmFileToEncode;

import java.io.IOException;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

public class Application {
    //Console console; coś takiego pewnie tutaj będzie

    private HuffmanAlgorithm huffmanAlgorithm = new HuffmanAlgorithm();

    public static void main(String[] args) {
        Application application = new Application();

        try {
            application.code("test.pgm");
        } catch (IOException e) {
            System.err.println("Brak pliku test.pgm");
        }

    }

    private void serveFileException(IOException e) {
        //TODO pewnie dojdzie gdzie obiekt Console czy coś takiego
    }

    public void code(String path) throws IOException {
        FileToEncode fileToEncode = new PgmFileToEncode(path);
        List<Byte> dataToEncode = fileToEncode.loadData();
        Coder coder = new Coder(huffmanAlgorithm);
        List<BitSet> encodedData = coder.encode(dataToEncode);
        String fileName = fileToEncode.getPath().getFileName().toString();
        EncodedFile encodedFileToSave = new EncodedFileImpl(fileName);
        encodedFileToSave.save(encodedData);

    }
}
