package net.femtoparsec.chess.drawer;

import net.femtoparsec.chess.board.Board;
import net.femtoparsec.chess.board.LineParser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TestDrawing {

  public static void main(String[] args) throws IOException {
    final var lineStr = "1...e5 2. d4 d6";
    final var line = LineParser.parse(lineStr,"en");

    final var board = Board.createStart();

    line.applyToBoard(board);

    final var img = BoardDrawer.draw(board,DrawParameter.builder().size(Size._128).build(),ImageProvider.cached());

    ImageIO.write(img,"PNG",new File("test.png"));

  }
}
