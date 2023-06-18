package net.femtoparsec.chess.drawer._private;

import lombok.RequiredArgsConstructor;
import net.femtoparsec.chess.board.Piece;
import net.femtoparsec.chess.drawer.ImageProvider;
import net.femtoparsec.chess.drawer.PieceImage;
import net.femtoparsec.chess.drawer.Size;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UncheckedIOException;

@RequiredArgsConstructor
public class DefaultImageProvider implements ImageProvider {

  @Override
  public BufferedImage getImage(Piece piece, Size size) {
    try {
      final var url = PieceImage.findImage(piece).getResourceUrl(size);
      return ImageIO.read(url);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
