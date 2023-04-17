package net.femtoparsec.chess.server;

import io.micronaut.runtime.Micronaut;

public class ServerLauncher {

  public static void main(String[] args) {
    Micronaut.run(MicronautFactory.class);
  }
}
