package net.femtoparsec.chess.cli;

import io.micronaut.runtime.Micronaut;
import net.femtoparsec.chess.server.ChessImageController;
import picocli.CommandLine;

@CommandLine.Command(name="server")
public class LaunchServer implements Runnable {

  @Override
  public void run() {
    Micronaut.run(ChessImageController.class);
  }
}
