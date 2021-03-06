package es.elka.koda.generator.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.ArrayList;
import java.util.List;
@Parameters
public class Commands {


    @Parameter(names = "-verbose", description = "Verbosity mode", hidden = true)
    private boolean verbose = false;

    @Parameter(names = "-length", description = "Length of sequence")
    private int length = 128;



    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
