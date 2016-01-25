package es.elka.koda.app.config;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.MissingCommandException;

public class CommandConsole {
    private JCommander jCommander;
    private CommandCoder commandCoder;

    public static CommandConsole createCommand() {
        CommandConsole console = new CommandConsole();
        console.commandCoder = new CommandCoder();
        console.jCommander = new JCommander();
        console.jCommander.addCommand("code", console.commandCoder);
        return console;
    }

    public boolean parseArgs(String[] args) {
        try {
            jCommander.parse(args);
        } catch (MissingCommandException e) {
            System.err.println("Unknown command");
        } finally {
            if (jCommander.getParsedCommand() == null) {
                jCommander.usage();
                return true;
            }
            return false;
        }
    }

    public String inputFileName() {
        return commandCoder.getInputFileName();
    }

}