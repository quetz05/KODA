package es.elka.koda.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.MissingCommandException;
import es.elka.koda.generator.config.CommandNormal;
import es.elka.koda.generator.config.Commands;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {


    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws InterruptedException {

        // Commands init

        Commands commands = new Commands();
        JCommander jc = new JCommander(new Commands());
        CommandNormal cmdNormal = new CommandNormal();
        jc.addCommand("normal", cmdNormal);

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
            default:
                throw new RuntimeException();
        }

        new Generator()
                .setDistribution(distribution)
                .generate(128)
                .drawHistogram()
                .saveFile();

    }


}