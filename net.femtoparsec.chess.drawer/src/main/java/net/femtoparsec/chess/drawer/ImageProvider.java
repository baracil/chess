package net.femtoparsec.chess.drawer;

import lombok.NonNull;
import net.femtoparsec.chess.board.Piece;
import net.femtoparsec.chess.drawer._private.CachedImageProvider;
import net.femtoparsec.chess.drawer._private.DefaultImageProvider;

import java.awt.image.BufferedImage;

public interface ImageProvider {


  @NonNull BufferedImage getImage(@NonNull Piece piece, @NonNull Size size);

  static ImageProvider cached() {
    return new CachedImageProvider(new DefaultImageProvider());
  }

}
