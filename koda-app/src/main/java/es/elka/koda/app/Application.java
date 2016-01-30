package es.elka.koda.app;

import es.elka.koda.app.algorithm.HuffmanAlgorithm;
import es.elka.koda.app.coder.Coder;
import es.elka.koda.app.config.CommandConsole;
import es.elka.koda.app.file.EncodedFile;
import es.elka.koda.app.file.EncodedFileImpl;
import es.elka.koda.app.file.FileToEncode;
import es.elka.koda.app.file.PgmFileToEncode;

import java.io.IOException;
import java.util.BitSet;
import java.util.List;

public class Application {
    //Console console; coś takiego pewnie tutaj będzie

    private HuffmanAlgorithm huffmanAlgorithm = new HuffmanAlgorithm();
    private int encodedDataSize;

    public static void main(String[] args) {
        CommandConsole commandConsole = CommandConsole.createCommand();

        if (commandConsole.parseArgs(args)) {
            return;
        }

        Application application = new Application();

        String path = commandConsole.inputFileName();

        try {
            application.code(path);
            if (commandConsole.debug()) {
                System.out.println("Średnia długość zakodowanego znaku: " +
                        application.calculateAverageSignCoded(application.encodedDataSize));
                System.out.println("Stopień entropii: " +
                        application.calculateEntropy(application.encodedDataSize));
            }
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
        encodedDataSize = encodedData.size();
    }

    private double calculateAverageSignCoded(int size) {
        long sum = huffmanAlgorithm.getTree().nodeList.stream()
                .filter(e -> e.symbol != null)
                .mapToLong(e -> huffmanAlgorithm.getDictionary().get(e.symbol).getLength() * e.weight)
                .sum();

        return (double) sum / size;
    }

    private double calculateEntropy(int size) {
        return (-1) * huffmanAlgorithm.getTree().nodeList.stream()
                .filter(e -> e.symbol != null)
                .mapToDouble(e -> (double) e.weight / size)
                .map(p -> p * Math.log(p) / Math.log(2))
                .sum();
    }
}
