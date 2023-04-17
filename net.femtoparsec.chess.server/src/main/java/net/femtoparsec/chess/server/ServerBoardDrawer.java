package net.femtoparsec.chess.server;

import lombok.NonNull;
import net.femtoparsec.chess.board.Line;
import net.femtoparsec.chess.drawer.DrawParameter;

import java.awt.image.BufferedImage;

public interface ServerBoardDrawer {

  @NonNull BufferedImage draw(@NonNull Line line, @NonNull DrawParameter parameter);
}
