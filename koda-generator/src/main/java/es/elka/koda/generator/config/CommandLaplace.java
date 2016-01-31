package es.elka.koda.generator.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Data generation with laplace distribution. ")
public class CommandLaplace {
    @Parameter(names = "--m", description = "Location parameter")
    private Double mu = 128.0;

    @Parameter(names = "--b", description = "Scale parameter (must be positive)")
    private Double beta = 20.0;

    public Double getMu() {
        return mu;
    }

    public void setMu(Double mu) {
        this.mu = mu;
    }

    public Double getBeta() {
        return beta;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }
}
