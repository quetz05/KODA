package es.elka.koda.app.file;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.List;

public class EncodedFileImpl implements EncodedFile {
    private final static String ENCODED_FILE_FORMAT = ".elkaes";
    private Path pathToSave;
    private StringBuilder pathBuilder = new StringBuilder();

    public EncodedFileImpl(String directionToSave, String fileName) {
        formatFilePathAddDirection(directionToSave);
        formatFilePathAddFileNameAndFormat(fileName);
        this.pathToSave = Paths.get(pathBuilder.toString());
    }

    public EncodedFileImpl(String fileName) {
        formatFilePathAddFileNameAndFormat(fileName);
        this.pathToSave = Paths.get(pathBuilder.toString());
    }

    private static byte[] mapToByteArray(List<BitSet> dataToMap) {
        byte[] mappedData = new byte[0];
        for (BitSet bits : dataToMap) {
            byte[] dataToAdd = bits.toByteArray();
            mappedData = ArrayUtils.addAll(mappedData, dataToAdd);
        }
        return mappedData;
    }

    private void formatFilePathAddDirection(String directionToSave) {
        pathBuilder.append(directionToSave);
        if (!directionToSave.endsWith(File.separator))
            pathBuilder.append(File.separator);
    }

    private void formatFilePathAddFileNameAndFormat(String fileName) {
        pathBuilder.append(fileName);
        pathBuilder.append(ENCODED_FILE_FORMAT);
    }

    @Override
    public void save(List<BitSet> tokens) throws IOException {
        byte[] bytes = mapToByteArray(tokens);
        Files.write(pathToSave, bytes);
    }
}
