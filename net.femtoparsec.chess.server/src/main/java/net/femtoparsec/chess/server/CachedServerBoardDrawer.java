package net.femtoparsec.chess.server;

import lombok.RequiredArgsConstructor;
import net.femtoparsec.chess.board.Line;
import net.femtoparsec.chess.drawer.DrawParameter;

import java.awt.image.BufferedImage;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CachedServerBoardDrawer implements ServerBoardDrawer {

  private final ServerBoardDrawer delegate;

  private final Map<Key, Reference<BufferedImage>> cache = new HashMap<>();

  @Override
  public BufferedImage draw(Line line, DrawParameter parameter) {
    final var key = new Key(line,parameter);
    final var reference = cache.get(key);
    final var image = reference == null ? null:reference.get();

    if (image != null) {
      return image;
    }

    final var newImage = delegate.draw(line,parameter);
    this.cache.put(key,new SoftReference<>(newImage));

    return newImage;
  }

  private record Key(Line line, DrawParameter parameter) {}
}
