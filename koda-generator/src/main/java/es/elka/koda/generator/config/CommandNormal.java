package es.elka.koda.generator.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Data generation with normal distribution. ")
public class CommandNormal {

    @Parameter(names = "--m", description = "Mean")
    private Double mean = 127.0;

    @Parameter(names = "--sd", description = "Standard deviation")
    private Double sd = 40.0;

    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public Double getSd() {
        return sd;
    }

    public void setSd(Double sd) {
        this.sd = sd;
    }
}