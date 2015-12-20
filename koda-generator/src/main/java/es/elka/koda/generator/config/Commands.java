package es.elka.koda.generator.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.ArrayList;
import java.util.List;

@Parameters(separators = "=")
public class Commands {

    @Parameter
    private List<String> parameters = new ArrayList<String>();

    @Parameter(names = "-verbose", description = "Verbosity mode", hidden = true)
    private boolean verbose = false;

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
}