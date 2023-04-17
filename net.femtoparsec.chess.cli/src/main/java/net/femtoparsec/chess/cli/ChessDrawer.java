package net.femtoparsec.chess.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(mixinStandardHelpOptions = true, subcommands = {CreateImage.class, LaunchServer.class})
public class ChessDrawer  {

    public static void main(String[] args) {
        new CommandLine(new ChessDrawer()).execute(args);
    }

}
