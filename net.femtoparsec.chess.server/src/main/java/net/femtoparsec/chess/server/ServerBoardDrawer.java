package net.femtoparsec.chess.server;

import net.femtoparsec.chess.board.Line;
import net.femtoparsec.chess.drawer.DrawParameter;

import java.awt.image.BufferedImage;

public interface ServerBoardDrawer {

  BufferedImage draw(Line line, DrawParameter parameter);
}
