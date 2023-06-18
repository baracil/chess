package net.femtoparsec.chess.drawer._private;

import lombok.RequiredArgsConstructor;
import net.femtoparsec.chess.board.Piece;
import net.femtoparsec.chess.drawer.ImageProvider;
import net.femtoparsec.chess.drawer.Size;

import java.awt.image.BufferedImage;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CachedImageProvider implements ImageProvider {

  private final ImageProvider delegate;
  private final Map<Key, Reference<BufferedImage>> cache = new HashMap<>();

  @Override
  public BufferedImage getImage(Piece piece, Size size) {
    final var key = new Key(piece,size);
    final var reference = cache.get(key);
    final var cached = reference == null ? null : reference.get();
    if (cached != null) {
      return cached;
    }
    final var value = delegate.getImage(piece,size);
    this.cache.put(key, new SoftReference<>(value));
    return value;
  }

  private record Key(Piece piece, Size size) {}

}
