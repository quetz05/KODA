package es.elka.koda.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.MissingCommandException;
import es.elka.koda.generator.config.CommandLaplace;
import es.elka.koda.generator.config.CommandNormal;
import es.elka.koda.generator.config.CommandUniform;
import es.elka.koda.generator.config.Commands;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.LaplaceDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {


    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws InterruptedException {

        // Commands init

        Commands commands = new Commands();
        JCommander jc = new JCommander(commands);
        CommandNormal cmdNormal = new CommandNormal();
        jc.addCommand("normal", cmdNormal);
        CommandLaplace cmdLaplace = new CommandLaplace();
        jc.addCommand("laplace", cmdLaplace);
        CommandUniform cmdUniform = new CommandUniform();
        jc.addCommand("uniform", cmdUniform);

        // Commands init end
        try {
            jc.parse(args);
        }catch(MissingCommandException e){
            logger.warning("Unknown command. ");
        }finally{
            if(jc.getParsedCommand() == null){
                logger.warning("You have to specify only one command. Command is mendatory. ");
                jc.usage();
                return;
            }
        }

        if(commands.isVerbose())
            logger.setLevel(Level.INFO);
        else
            logger.setLevel(Level.OFF);

        logger.info("Verbose mode ON");

        AbstractRealDistribution distribution;

        logger.info("Picking distribution. ");

        switch (jc.getParsedCommand()){
            case "normal":
                distribution =  new NormalDistribution(cmdNormal.getMean(), cmdNormal.getSd());
                break;
            case "laplace":
                distribution = new LaplaceDistribution(cmdLaplace.getMu(), cmdLaplace.getBeta());
                break;
            case "uniform":
                distribution = new UniformRealDistribution(0.0, 256.0);
                break;
            default:
                throw new RuntimeException();
        }

        new Generator()
                .setDistribution(distribution)
                .generate(commands.getLength())
                .drawHistogram()
                .saveFile();

    }


}