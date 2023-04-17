package net.femtoparsec.chess.server;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.chess.board.Board;
import net.femtoparsec.chess.board.Line;
import net.femtoparsec.chess.drawer.BoardDrawer;
import net.femtoparsec.chess.drawer.DrawParameter;
import net.femtoparsec.chess.drawer.ImageProvider;

import java.awt.image.BufferedImage;

@RequiredArgsConstructor
public class DefaultServerBoardDrawer implements ServerBoardDrawer {

  private final @NonNull ImageProvider imageProvider;

  @Override
  public @NonNull BufferedImage draw(@NonNull Line line, @NonNull DrawParameter parameter) {
    final var board = Board.createStart();
    line.applyToBoard(board);
    return BoardDrawer.draw(board,parameter,imageProvider);
  }
}
