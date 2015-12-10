package es.elka.koda.app;

import es.elka.koda.app.file.EncodedFile;
import es.elka.koda.app.file.EncodedFileImpl;
import es.elka.koda.app.file.FileToEncode;
import es.elka.koda.app.file.PgmFileToEncode;

import java.io.IOException;
import java.util.List;

public class Application {
    //Console console; coś takiego pewnie tutaj będzie

    public static void main(String[] args) {


    }

    private void serveFileException(IOException e) {
        //TODO pewnie dojdzie gdzie obiekt Console czy coś takiego
    }

    private void code(String path) throws IOException {
        FileToEncode fileToEncode = new PgmFileToEncode(path);
        List<Byte> dataToEncode = fileToEncode.loadData();
//         Coder coder = new Coder();
//         List<BitSet> encodedData = coder.encode();
        String fileName = fileToEncode.getPath().getFileName().toString();
        EncodedFile encodedFileToSave = new EncodedFileImpl(fileName);
//         encodedFileToSave.save();

    }
}
