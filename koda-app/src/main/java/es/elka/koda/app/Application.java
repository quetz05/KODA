package es.elka.koda.app;

import es.elka.koda.app.algorithm.HuffmanAlgorithm;
import es.elka.koda.app.coder.Coder;
import es.elka.koda.app.config.CommandConsole;
import es.elka.koda.app.decoder.Decoder;
import es.elka.koda.app.file.EncodedFile;
import es.elka.koda.app.file.EncodedFileImpl;
import es.elka.koda.app.file.FileToEncode;
import es.elka.koda.app.file.PgmFileToEncode;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.List;

public class Application {
    //Console console; coś takiego pewnie tutaj będzie

    private HuffmanAlgorithm huffmanAlgorithm = new HuffmanAlgorithm();

    public static void main(String[] args) {
        CommandConsole commandConsole = CommandConsole.createCommand();

        if (commandConsole.parseArgs(args)) {
            return;
        }

        Application application = new Application();

        String path = commandConsole.inputFileName();

        try {
            application.decode(path);
            // application.code(path);
        } catch (IOException e) {
            application.serveFileException(e);
        }

    }

    private void serveFileException(IOException e) {
        System.err.println("Problem with processing file. Exception message: " + e.getMessage());
        e.printStackTrace();
    }

    public void code(String path) throws IOException {
        FileToEncode fileToEncode = new PgmFileToEncode(path);
        List<Byte> dataToEncode = fileToEncode.loadData();
        Coder coder = new Coder(huffmanAlgorithm);
        List<BitSet> encodedData = coder.encode(dataToEncode);
        String fileName = fileToEncode.getPath().getFileName().toString();
        EncodedFile encodedFileToSave = new EncodedFileImpl(fileName);
        encodedFileToSave.save(encodedData, huffmanAlgorithm.getDictionary());

    }

    public void decode(String path) throws IOException {

        byte[] bytes = IOUtils.toByteArray(new FileInputStream(path));

        Decoder decoder = new Decoder(bytes);
        decoder.decode();
    }

}
