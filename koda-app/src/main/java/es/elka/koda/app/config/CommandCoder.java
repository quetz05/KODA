package es.elka.koda.app.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Runs association rules discover with chosen algorithm")
public class CommandCoder {

    @Parameter(names = {"--input", "-i"},
            description = "Input file to encode", required = true)
    private String inputFileName;


    public String getInputFileName() {
        return inputFileName;
    }

}
