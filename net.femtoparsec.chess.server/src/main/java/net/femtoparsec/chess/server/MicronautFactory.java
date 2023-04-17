package net.femtoparsec.chess.server;

import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import net.femtoparsec.chess.drawer._private.CachedImageProvider;
import net.femtoparsec.chess.drawer._private.DefaultImageProvider;

@Factory
public class MicronautFactory {

  @Singleton
  public ServerBoardDrawer createDrawer() {
    return new CachedServerBoardDrawer(new DefaultServerBoardDrawer(new CachedImageProvider(new DefaultImageProvider())));
  }
}
