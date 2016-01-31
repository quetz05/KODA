package es.elka.koda.app.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters
public class CommandCoder {

    @Parameter(names = {"--input", "-i"},
            description = "Input file", required = true)
    private String inputFileName;

    @Parameter(names = {"--debug", "-d"},
            description = "Debug flag")
    private boolean debug = false;

    public String getInputFileName() {
        return inputFileName;
    }

    public boolean isDebug() {
        return debug;
    }

}
