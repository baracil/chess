package net.femtoparsec.chess.drawer;

import net.femtoparsec.chess.board.Piece;
import net.femtoparsec.chess.drawer._private.CachedImageProvider;
import net.femtoparsec.chess.drawer._private.DefaultImageProvider;

import java.awt.image.BufferedImage;

public interface ImageProvider {


  BufferedImage getImage(Piece piece, Size size);

  static ImageProvider cached() {
    return new CachedImageProvider(new DefaultImageProvider());
  }

}
